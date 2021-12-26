package org.example.pulsar.config;

import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.PulsarAdminException;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.common.policies.data.ClusterData;
import org.apache.pulsar.common.policies.data.TenantInfo;

import java.util.Set;

public class PulsarConfig {
    public PulsarAdmin admin;

    public static final String CLUSTER = "cluster-1";
    public static final String TENANT = "tn1";
    public static final String NAMESPACE = "ns1";
    public static final String ENDPOINT = "cameltest";
    public static final String PARAMETERS = "?producerName=clientProd";
    public static final String TEST_ENDPOINT = String.format("pulsar:%s/%s}/%s", TENANT, NAMESPACE, ENDPOINT);
    public static final String TEST_URI = String.format("pulsar:non-persistent://%s/%s/%s", TENANT, NAMESPACE, ENDPOINT);
    public static final String SERVICE_URL = "http://localhost:8080";
    public static final String BROKER_SERVICE_URL = "";
    public static final Set<String> CLUSTERS = Set.of("standalone", "cluster-1");
    public static final boolean USE_TLS = false;
    public static final boolean TLS_ALLOW_INSECURE_CONNECTION = true;
    public static final String TLS_TRUST_CERTS_FILEPATH = null;

    public PulsarConfig() throws PulsarClientException, PulsarAdminException {
        admin = PulsarAdmin.builder()
                .serviceHttpUrl(SERVICE_URL)
//                .tlsTrustCertsFilePath(TLS_TRUST_CERTS_FILEPATH)
//                .allowTlsInsecureConnection(TLS_ALLOW_INSECURE_CONNECTION)
                .build();

        createData(admin);
    }

//    public void safeDeleteData(PulsarAdmin admin) {
//        try {
//            admin.namespaces().deleteNamespace(TENANT + "/" + NAMESPACE);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            admin.tenants().deleteTenant(TENANT);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            admin.clusters().deleteCluster(CLUSTER);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void createData(PulsarAdmin admin) throws PulsarAdminException {
        try {
            createCluster(admin);
        } catch (PulsarAdminException e) {}

        try {
            createTenant(admin);
        } catch (PulsarAdminException e) {}

        try {
            createNamespace(admin);
        } catch (PulsarAdminException e) {}

    }

    public void createCluster(PulsarAdmin admin) throws PulsarAdminException {
        admin.clusters().createCluster(
                CLUSTER,
                ClusterData.builder()
                        .serviceUrl(SERVICE_URL)
                        .brokerServiceUrl(BROKER_SERVICE_URL)
                        .build()
        );
    }

    public void createTenant(PulsarAdmin admin) throws PulsarAdminException {
        admin.tenants().createTenant(TENANT, TenantInfo.builder()
                .allowedClusters(CLUSTERS)
                .build());
    }

    public void createNamespace(PulsarAdmin admin) throws PulsarAdminException {
        admin.namespaces().createNamespace(TENANT + "/" + NAMESPACE, CLUSTERS);
    }
}
