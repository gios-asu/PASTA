/*
 *
 * $Date$
 * $Author$
 * $Revision$
 *
 * Copyright 2010 the University of New Mexico.
 *
 * This work was supported by National Science Foundation Cooperative
 * Agreements #DEB-0832652 and #DEB-0936498.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 *
 */

package edu.lternet.pasta.common.security.token;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import edu.lternet.pasta.common.security.token.BasicAuthToken;

/**
 * Dummy HTTP headers except for Basic Access Authentication.
 */
public class DummyBasicHttpHeaders implements HttpHeaders {

    private final String user;
    private final String password;
    
    /**
     * Constructs an HTTP header object with the provided user and password. If
     * the provided user is {@code null}, the authorization credentials will be
     * non-existent.
     * 
     * @param user the user ID.
     * @param password the user's password.
     */
    public DummyBasicHttpHeaders(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * Returns Basic Access Authentication credentials, if they exist, when
     * {@link HttpHeaders#AUTHORIZATION} is provided. Throws
     * {@link UnsupportedOperationException} otherwise.
     * 
     * @param header
     *            the HTTP header.
     * 
     * @return a list containing Basic Access Authentication credentials when
     *         {@code HttpHeaders.AUTHORIZATION} is provided.
     */
    @Override
    public List<String> getRequestHeader(String header) {

        if (header.equals(AUTHORIZATION)) {
            
            if (user == null) {
                return Collections.emptyList();
            }
            
            String token = BasicAuthToken.makeTokenString(user, password);
            
            return Arrays.asList(token.split(" "));
        }
        
        throw new UnsupportedOperationException();
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     */
    @Override
    public List<Locale> getAcceptableLanguages() {
        throw new UnsupportedOperationException();
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     */
    @Override
    public List<MediaType> getAcceptableMediaTypes() {
        throw new UnsupportedOperationException();
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     */
    @Override
    public Map<String, Cookie> getCookies() {
        throw new UnsupportedOperationException();
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     */
    @Override
    public Locale getLanguage() {
        throw new UnsupportedOperationException();
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     */
    @Override
    public MediaType getMediaType() {
        throw new UnsupportedOperationException();
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     */
    @Override
    public MultivaluedMap<String, String> getRequestHeaders() {
        throw new UnsupportedOperationException();
    }

}
