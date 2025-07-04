package com.mcossu.tokenjwt.service;

public interface TokenService {

    /**
     * Genera un token JWT per l'utente specificato.
     *
     * @param uid    il subject che identifica l'utente
     * @param issuer l'emittente del token
     * @param secret la chiave segreta per la firma del token
     * @return una stringa rappresentante il token JWT generato
     */
    String generateToken(String uid, String issuer, String secret);
}
