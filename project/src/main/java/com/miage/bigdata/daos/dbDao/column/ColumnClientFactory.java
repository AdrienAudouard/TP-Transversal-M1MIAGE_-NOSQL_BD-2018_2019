package com.miage.bigdata.daos.dbDao.column;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.JdkSSLOptions;
import com.datastax.driver.core.RemoteEndpointAwareJdkSSLOptions;
import com.datastax.driver.core.Session;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

class ColumnClientFactory {
    private final static String contactPoint = "db-column.cassandra.cosmos.azure.com";
    private final static String password = "tPDf9rs4GaFQkrBFxjVsuIV6Q2zBPfxOXN3Sa5PnbK0F4u2Izu32DZYo7au8Hfr5uOC5PzTPCVpt2RcqI2p9vQ==";
    private final static String keyspace = "miage";
    private final static String username = "db-column";
    private final static String keystorePath = System.getProperty("java.home") + "/lib/security/cacerts";
    private final static String keystorePwd = "changeit";
    private static Session session;

    public static Session getCassandraSession() {
        if (session == null) {
            SSLContext sc;
            final KeyManagerFactory kmf;

            try {
                File sslKeyStoreFile = new File(keystorePath);

                final KeyStore keyStore = KeyStore.getInstance("JKS");

                try (final InputStream is = new FileInputStream(sslKeyStoreFile)) {
                    keyStore.load(is, keystorePwd.toCharArray());
                }

                kmf = KeyManagerFactory.getInstance(KeyManagerFactory
                        .getDefaultAlgorithm());
                kmf.init(keyStore, keystorePwd.toCharArray());
                final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory
                        .getDefaultAlgorithm());
                tmf.init(keyStore);

                sc = SSLContext.getInstance("TLSv1.2");

                sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

                JdkSSLOptions sslOptions = RemoteEndpointAwareJdkSSLOptions.builder()
                        .withSSLContext(sc)
                        .build();

                Cluster cluster = Cluster.builder()
                        .addContactPoint(contactPoint)
                        .withPort(10350)
                        .withCredentials(username, password)
                        .withSSL(sslOptions)
                        .build();

                session = cluster.connect(keyspace);
            } catch (NoSuchAlgorithmException | UnrecoverableKeyException | KeyStoreException | KeyManagementException | IOException | CertificateException e) {
                e.printStackTrace();
            }
        }

        return session;
    }
}
