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

package edu.lternet.pasta.common.security.access;

import edu.lternet.pasta.common.security.token.AuthToken;

/**
 * <p>
 * Used to represent an access controller for authorization tokens. An access
 * controller examines a user's attributes to determine if he/she has permission
 * to perform a particular operation. This determination is made by comparing
 * the user's attributes (contained in the authorization token) to an access
 * control rule and to the submitter of the resource to which the rule applies.
 * </p>
 * <p>
 * The operations include 'read', 'write', 'change permissions', and 'all of the
 * above'.
 * </p>
 *
 */
public interface AuthTokenAccessController {

    /**
     * Indicates if the user with the provided attributes has permission to
     * perform the {@code read} operation.
     *
     * @param authToken
     *            the token containing the user's attributes.
     * @param accessControlRule
     *            a string containing the access control rule.
     * @param resourceSubmitter
     *            the submitter of the resource for which access control is
     *            being applied.
     *
     * @return {@code true} if the access control rule allows the specified user
     *         to {@code read}; {@code false} if the user is denied.
     */
    public boolean canRead(AuthToken authToken,
                           String accessControlRule,
                           String resourceSubmitter);

    /**
     * Indicates if the user with the provided attributes has permission to
     * perform the {@code write} operation.
     *
     * @param authToken
     *            the token containing the user's attributes.
     * @param accessControlRule
     *            a string containing the access control rule.
     * @param resourceSubmitter
     *            the submitter of the resource for which access control is
     *            being applied.
     *
     * @return {@code true} if the access control rule allows the specified user
     *         to {@code write}; {@code false} if the user is denied.
     */
    public boolean canWrite(AuthToken authToken,
                            String accessControlRule,
                            String resourceSubmitter);

    /**
     * Indicates if the user with the provided attributes has permission to
     * perform the {@code change permission} operation.
     *
     * @param authToken
     *            the token containing the user's attributes.
     * @param accessControlRule
     *            a string containing the access control rule.
     * @param resourceSubmitter
     *            the submitter of the resource for which access control is
     *            being applied.
     *
     * @return {@code true} if the access control rule allows the specified user
     *         to {@code change permission}; {@code false} if the user is
     *         denied.
     */
    public boolean canChangePermission(AuthToken authToken,
                                       String accessControlRule,
                                       String resourceSubmitter);

    /**
     * Indicates if the user with the provided attributes has permission to
     * perform {@code all of the above} operations.
     *
     * @param authToken
     *            the token containing the user's attributes.
     * @param accessControlRule
     *            a string containing the access control rule.
     * @param resourceSubmitter
     *            the submitter of the resource for which access control is
     *            being applied.
     *
     * @return {@code true} if the access control rule allows the specified user
     *         to perform {@code all of the above}; {@code false} if the user is
     *         denied.
     */
    public boolean canAll(AuthToken authToken,
                          String accessControlRule,
                          String resourceSubmitter);

}
