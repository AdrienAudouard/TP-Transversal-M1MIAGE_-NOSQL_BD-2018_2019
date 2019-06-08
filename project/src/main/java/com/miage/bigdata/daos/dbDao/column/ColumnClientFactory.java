package com.miage.bigdata.daos.dbDao.column;

import com.datastax.driver.core.*;
import com.miage.bigdata.utils.Configuration;

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
    private final static String keystorePath = System.getProperty("java.home") + "/lib/security/cacerts";
    private static Session session;

    public static Session getCassandraSession() {
        Configuration configuration = Configuration.COLUMN;

        if (session == null) {
            SSLContext sc;
            final KeyManagerFactory kmf;

            try {
                String keystorePwd = configuration.getProperty("keystorePwd");
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
                        .addContactPoint(configuration.getProperty("contactPoint"))
                        .withPort(10350)
                        .withCredentials(configuration.getProperty("username"), configuration.getProperty("password"))
                        .withSSL(sslOptions)
                        .build();

                session = cluster.connect(configuration.getProperty("keyspace"));
            } catch (NoSuchAlgorithmException | UnrecoverableKeyException | KeyStoreException | KeyManagementException | IOException | CertificateException e) {
                e.printStackTrace();
            }
        }

        return session;
    }
}
