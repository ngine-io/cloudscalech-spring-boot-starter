package io.ngine.cloudscale.client;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.ngine.cloudscale.client.model.AddressSpec;
import io.ngine.cloudscale.client.model.BucketMetrics;
import io.ngine.cloudscale.client.model.FloatingIp;
import io.ngine.cloudscale.client.model.InterfaceSpec;
import io.ngine.cloudscale.client.model.LoadBalancerPoolMember;
import io.ngine.cloudscale.client.model.NetworkCreateRequest;
import io.ngine.cloudscale.client.model.ProjectLogRecord;
import io.ngine.cloudscale.client.model.Server;
import io.ngine.cloudscale.client.model.ServerCreateRequest;
import io.ngine.cloudscale.client.model.SubnetUpdateRequest;
import io.ngine.cloudscale.client.model.VolumeSpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class CloudscaleClientTests {

    private static final String API = "https://api.cloudscale.ch/v1";

    private static final String SERVER_JSON = """
            {
              "href": "https://api.cloudscale.ch/v1/servers/47cec963-fcd2-482f-bdb6-24461b2d47b1",
              "uuid": "47cec963-fcd2-482f-bdb6-24461b2d47b1",
              "name": "db-main",
              "created_at": "2019-05-27T16:45:32.241824Z",
              "status": "running",
              "zone": {"slug": "lpg1"},
              "flavor": {"slug": "flex-4-2", "name": "Flex-4-2", "vcpu_count": 2, "memory_gb": 4},
              "image": {"slug": "debian-13", "name": "Debian 13", "operating_system": "Debian",
                        "default_username": "debian"},
              "volumes": [{"href": null, "uuid": null, "name": "db-main-root", "size_gb": 50, "type": "ssd"}],
              "interfaces": [{
                "type": "public",
                "network": {"href": "https://api.cloudscale.ch/v1/networks/36dac305", "uuid": "36dac305",
                            "name": "public"},
                "mac_address": "41:42:43:44:45:46",
                "addresses": [{
                  "version": 4, "address": "185.98.122.110", "prefix_length": "24",
                  "gateway": "185.98.122.1", "reverse_ptr": "185-98-122-110.cust.cloudscale.ch",
                  "subnet": {"href": "https://api.cloudscale.ch/v1/subnets/92c70b2f", "uuid": "92c70b2f",
                             "cidr": "185.98.122.0/24"}
                }]
              }],
              "ssh_fingerprints": null,
              "ssh_host_keys": null,
              "server_groups": [],
              "anti_affinity_with": [],
              "tags": {"env": "prod"},
              "some_future_attribute": 42
            }
            """;

    private MockRestServiceServer server;

    private CloudscaleClient client;

    @BeforeEach
    void setUp() {
        RestClient.Builder builder = RestClient.builder();
        this.server = MockRestServiceServer.bindTo(builder).build();
        this.client = CloudscaleClient.builder().apiToken("secret-token").restClientBuilder(builder).build();
    }

    @Test
    void listServersMapsSnakeCaseAndSendsToken() {
        server.expect(requestTo(API + "/servers"))
            .andExpect(method(HttpMethod.GET))
            .andExpect(header(HttpHeaders.AUTHORIZATION, "Bearer secret-token"))
            .andRespond(withSuccess("[" + SERVER_JSON + "]", MediaType.APPLICATION_JSON));

        List<Server> servers = client.servers().list();

        assertThat(servers).singleElement().satisfies((s) -> {
            assertThat(s.uuid()).isEqualTo("47cec963-fcd2-482f-bdb6-24461b2d47b1");
            assertThat(s.isRunning()).isTrue();
            assertThat(s.createdAt()).isEqualTo(OffsetDateTime.of(2019, 5, 27, 16, 45, 32, 241824000, ZoneOffset.UTC));
            assertThat(s.flavor().vcpuCount()).isEqualTo(2);
            assertThat(s.image().defaultUsername()).isEqualTo("debian");
            assertThat(s.volumes().get(0).uuid()).isNull();
            assertThat(s.interfaces().get(0).addresses().get(0).prefixLength()).isEqualTo(24);
            assertThat(s.interfaces().get(0).addresses().get(0).subnet().cidr()).isEqualTo("185.98.122.0/24");
            assertThat(s.sshHostKeys()).isNull();
            assertThat(s.tags()).containsEntry("env", "prod");
        });
        server.verify();
    }

    @Test
    void listWithTagKeyAndValueFilter() {
        server.expect(requestTo(API + "/servers?tag:env=prod%20eu"))
            .andRespond(withSuccess("[]", MediaType.APPLICATION_JSON));

        assertThat(client.servers().list(TagFilter.of("env", "prod eu"))).isEmpty();
        server.verify();
    }

    @Test
    void listWithTagKeyOnlyFilter() {
        server.expect(requestTo(API + "/volumes?tag:backup"))
            .andRespond(withSuccess("[]", MediaType.APPLICATION_JSON));

        assertThat(client.volumes().list(TagFilter.parse("backup"))).isEmpty();
        server.verify();
    }

    @Test
    void createServerSendsOnlySetAttributes() {
        server.expect(requestTo(API + "/servers"))
            .andExpect(method(HttpMethod.POST))
            .andExpect(content().json("""
                    {
                      "name": "db-main",
                      "flavor": "flex-4-2",
                      "image": "debian-13",
                      "zone": "lpg1",
                      "volume_size_gb": 50,
                      "volumes": [{"size_gb": 200, "type": "bulk"}],
                      "interfaces": [
                        {"network": "public"},
                        {"network": "2db69ba3", "addresses": []},
                        {"addresses": [{"subnet": "4a47e742", "address": "172.16.1.10"}]}
                      ],
                      "ssh_keys": ["ssh-ed25519 AAAA"],
                      "use_ipv6": true,
                      "tags": {"env": "prod"}
                    }
                    """, JsonCompareMode.STRICT))
            .andRespond(withStatus(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(SERVER_JSON));

        Server created = client.servers()
            .create(new ServerCreateRequest("db-main", "flex-4-2", "debian-13").zone("lpg1")
                .volumeSizeGb(50)
                .volumes(List.of(VolumeSpec.bulk(200)))
                .interfaces(List.of(InterfaceSpec.publicNetwork(),
                        InterfaceSpec.privateNetworkWithoutAddress("2db69ba3"),
                        InterfaceSpec.withAddresses(AddressSpec.fixed("4a47e742", "172.16.1.10"))))
                .sshKeys(List.of("ssh-ed25519 AAAA"))
                .useIpv6(true)
                .tag("env", "prod"));

        assertThat(created.name()).isEqualTo("db-main");
        server.verify();
    }

    @Test
    void serverActions() {
        server.expect(requestTo(API + "/servers/abc/stop")).andExpect(method(HttpMethod.POST)).andRespond(withNoContent());
        server.expect(requestTo(API + "/servers/abc/start")).andExpect(method(HttpMethod.POST)).andRespond(withNoContent());
        server.expect(requestTo(API + "/servers/abc/reboot")).andExpect(method(HttpMethod.POST)).andRespond(withNoContent());
        server.expect(requestTo(API + "/servers/abc")).andExpect(method(HttpMethod.DELETE)).andRespond(withNoContent());

        client.servers().stop("abc");
        client.servers().start("abc");
        client.servers().reboot("abc");
        client.servers().delete("abc");
        server.verify();
    }

    @Test
    void updateWithRawPayloadKeepsExplicitNull() {
        server.expect(requestTo(API + "/subnets/abc"))
            .andExpect(method(HttpMethod.PATCH))
            .andExpect(content().json("{\"dns_servers\": [\"192.168.42.11\"]}",
                    JsonCompareMode.STRICT))
            .andRespond(withNoContent());
        server.expect(requestTo(API + "/subnets/abc"))
            .andExpect(method(HttpMethod.PATCH))
            .andExpect(content().json("{\"gateway_address\": null}", JsonCompareMode.STRICT))
            .andRespond(withNoContent());

        client.subnets().update("abc", new SubnetUpdateRequest().dnsServers(List.of("192.168.42.11")));
        Map<String, Object> payload = new HashMap<>();
        payload.put("gateway_address", null);
        client.subnets().update("abc", payload);
        server.verify();
    }

    @Test
    void apiErrorIsTranslated() {
        server.expect(requestTo(API + "/servers/missing"))
            .andRespond(withStatus(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                .body("{\"detail\": \"Not found.\"}"));

        assertThatExceptionOfType(CloudscaleApiException.class).isThrownBy(() -> client.servers().get("missing"))
            .satisfies((ex) -> {
                assertThat(ex.getStatusCode()).isEqualTo(404);
                assertThat(ex.isNotFound()).isTrue();
                assertThat(ex.getMessage()).isEqualTo("API Response Error (404): Not found.");
            });
    }

    @Test
    void validationErrorKeepsBody() {
        server.expect(requestTo(API + "/networks"))
            .andRespond(withStatus(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                .body("{\"mtu\": [\"Ensure this value is less than or equal to 9000.\"]}"));

        assertThatExceptionOfType(CloudscaleApiException.class)
            .isThrownBy(() -> client.networks()
                .create(new NetworkCreateRequest("net").mtu(10000)))
            .satisfies((ex) -> assertThat(ex.getResponseBody()).contains("less than or equal to 9000"));
    }

    @Test
    void floatingIpIdentifiedByAddress() {
        server.expect(requestTo(API + "/floating-ips/2001%3Adb8%3A%3Acafe"))
            .andRespond(withSuccess("""
                    {"href": "https://api.cloudscale.ch/v1/floating-ips/2001:db8::cafe",
                     "created_at": "2019-05-29T13:18:42.505197Z", "network": "2001:db8::cafe/128",
                     "ip_version": 6, "server": null, "load_balancer": null, "region": {"slug": "lpg"},
                     "type": "regional", "next_hop": "2001:db8:dead:beef::42", "reverse_ptr": null, "tags": {}}
                    """, MediaType.APPLICATION_JSON));

        FloatingIp ip = client.floatingIps().get("2001:db8::cafe");

        assertThat(ip.networkId()).isEqualTo("2001:db8::cafe");
        assertThat(ip.region().slug()).isEqualTo("lpg");
        server.verify();
    }

    @Test
    void poolMembersAreNestedInPool() {
        server.expect(requestTo(API + "/load-balancers/pools/pool-1/members?tag:role=web"))
            .andRespond(withSuccess("""
                    [{"href": "x", "uuid": "m-1", "name": "web-0", "created_at": "2023-02-21T13:11:48.118903Z",
                      "enabled": true, "pool": {"href": "p", "uuid": "pool-1", "name": "web-lb-pool"},
                      "load_balancer": {"href": "l", "uuid": "lb-1", "name": "web-lb"},
                      "subnet": {"href": "s", "uuid": "s-1", "cidr": "10.11.12.0/24"}, "address": "10.11.12.107",
                      "protocol_port": 8080, "monitor_port": null, "monitor_status": "up", "tags": {}}]
                    """, MediaType.APPLICATION_JSON));

        List<LoadBalancerPoolMember> members = client.loadBalancerPoolMembers().list("pool-1", TagFilter.of("role", "web"));

        assertThat(members).singleElement().extracting(LoadBalancerPoolMember::protocolPort).isEqualTo(8080);
        server.verify();
    }

    @Test
    void bucketMetricsWithRepeatedFilters() {
        server.expect(requestTo(API + "/metrics/buckets?start=2019-03-19&end=2019-03-20&bucket_name=a&bucket_name=b"))
            .andRespond(withSuccess("""
                    {"start": "2019-03-18T23:00:00Z", "end": "2019-03-20T23:00:00Z",
                     "data": [{"subject": {"name": "a", "objects_user_id": "u1"},
                               "time_series": [{"start": "2019-03-18T23:00:00Z", "end": "2019-03-20T23:00:00Z",
                                                "usage": {"requests": 561, "object_count": 1105,
                                                          "storage_bytes": 1729, "received_bytes": 2465,
                                                          "sent_bytes": 2821}}]}]}
                    """, MediaType.APPLICATION_JSON));

        BucketMetrics metrics = client.metrics()
            .buckets(LocalDate.of(2019, 3, 19), LocalDate.of(2019, 3, 20), List.of("a", "b"), List.of());

        assertThat(metrics.data().get(0).timeSeries().get(0).usage().storageBytes()).isEqualTo(1729L);
        server.verify();
    }

    @Test
    void projectLogsFollowPagination() {
        server.expect(requestTo(API + "/project-logs?start=2025-09-03T11%3A00%2B02%3A00"))
            .andRespond(withSuccess("""
                    {"next": "https://api.cloudscale.ch/v1/project-logs?cursor=10025",
                     "poll_more": "https://api.cloudscale.ch/v1/project-logs?cursor=10025",
                     "results": [{"ip_address": null, "action": "server_pause", "message": "paused",
                                  "timestamp": "2025-09-03T17:20:11.435287Z", "actor": {"admin": {}},
                                  "subject": {"project": {"uuid": "p-1", "name": "My Project"}}}]}
                    """, MediaType.APPLICATION_JSON));
        server.expect(requestTo(API + "/project-logs?cursor=10025"))
            .andRespond(withSuccess("""
                    {"next": null, "poll_more": "https://api.cloudscale.ch/v1/project-logs?cursor=10640",
                     "results": [{"ip_address": "2001:db8::f", "action": "volume_create", "message": "created",
                                  "timestamp": "2025-09-03T11:18:02.609799Z",
                                  "actor": {"api_token": {"uuid": "t-1", "description": "prod-csi"}},
                                  "subject": {"project": {"uuid": "p-1", "name": "My Project"}}}]}
                    """, MediaType.APPLICATION_JSON));

        List<ProjectLogRecord> records = client.projectLogs()
            .listAll(OffsetDateTime.of(2025, 9, 3, 11, 0, 0, 0, ZoneOffset.ofHours(2)), null);

        assertThat(records).hasSize(2);
        assertThat(records.get(0).actor().isAdmin()).isTrue();
        assertThat(records.get(1).actor().apiToken().description()).isEqualTo("prod-csi");
        server.verify();
    }

    @Test
    void refusesToFollowForeignUrls() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> client.projectLogs().follow("https://evil.example.com/v1/project-logs?cursor=1"));
    }

    @Test
    void tokenIsRequired() {
        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> CloudscaleClient.builder().build());
    }
}
