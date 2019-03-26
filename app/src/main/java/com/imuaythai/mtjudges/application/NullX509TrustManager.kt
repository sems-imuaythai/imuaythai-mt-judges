package com.imuaythai.mtjudges.application
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;


class NullX509TrustManager(): X509TrustManager{


    /**
     * Does nothing.
     *
     * @param chain
     * certificate chain
     * @param authType
     * authentication type
     */
    @Throws(CertificateException::class)
    override fun checkClientTrusted(
        chain: Array<X509Certificate>,
        authType: String
    ) {
        // Does nothing
    }

    /**
     * Does nothing.
     *
     * @param chain
     * certificate chain
     * @param authType
     * authentication type
     */
    @Throws(CertificateException::class)
    override fun checkServerTrusted(
        chain: Array<X509Certificate>,
        authType: String
    ) {
        // Does nothing
    }

    /**
     * Gets a list of accepted issuers.
     *
     * @return empty array
     */
    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return arrayOf()
        // Does nothing
    }

}