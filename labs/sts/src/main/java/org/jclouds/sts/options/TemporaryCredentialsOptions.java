/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.sts.options;

import org.jclouds.http.options.BaseHttpRequestOptions;

import com.google.common.base.Objects;
import com.google.common.collect.Multimap;

/**
 * Options used to get a session token.
 * 
 * @see <a href=
 *      "http://docs.aws.amazon.com/STS/latest/APIReference/API_GetSessionToken.html"
 *      />
 * 
 * @author Adrian Cole
 */
public class TemporaryCredentialsOptions extends BaseHttpRequestOptions implements Cloneable {

   // long as this is a more typical unit for duration, hence less casting
   private Long durationSeconds;
   private String tokenCode;
   private String serialNumber;

   /**
    * The identification number of the MFA device for the user.
    */
   public TemporaryCredentialsOptions serialNumber(String serialNumber) {
      this.serialNumber = serialNumber;
      return this;
   }

   /**
    * The duration, in seconds, that the credentials should remain valid. 12
    * hours is default. 15 minutes is current minimum.
    */
   public TemporaryCredentialsOptions durationSeconds(long durationSeconds) {
      this.durationSeconds = durationSeconds;
      return this;
   }

   /**
    * The value provided by the MFA device.
    */
   public TemporaryCredentialsOptions tokenCode(String tokenCode) {
      this.tokenCode = tokenCode;
      return this;
   }

   public static class Builder {

      /**
       * @see TemporaryCredentialsOptions#serialNumber
       */
      public static TemporaryCredentialsOptions serialNumber(String serialNumber) {
         return new TemporaryCredentialsOptions().serialNumber(serialNumber);
      }

      /**
       * @see TemporaryCredentialsOptions#durationSeconds
       */
      public static TemporaryCredentialsOptions durationSeconds(long durationSeconds) {
         return new TemporaryCredentialsOptions().durationSeconds(durationSeconds);
      }

      /**
       * @see TemporaryCredentialsOptions#tokenCode
       */
      public static TemporaryCredentialsOptions tokenCode(String tokenCode) {
         return new TemporaryCredentialsOptions().tokenCode(tokenCode);
      }
   }

   @Override
   public Multimap<String, String> buildFormParameters() {
      Multimap<String, String> params = super.buildFormParameters();
      if (serialNumber != null)
         params.put("SerialNumber", serialNumber.toString());
      if (durationSeconds != null)
         params.put("DurationSeconds", durationSeconds.toString());
      if (tokenCode != null)
         params.put("TokenCode", tokenCode);
      return params;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public int hashCode() {
      return Objects.hashCode(serialNumber, durationSeconds, tokenCode);
   }

   @Override
   public TemporaryCredentialsOptions clone() {
      return new TemporaryCredentialsOptions().serialNumber(serialNumber).durationSeconds(durationSeconds)
            .tokenCode(tokenCode);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      TemporaryCredentialsOptions other = TemporaryCredentialsOptions.class.cast(obj);
      return Objects.equal(this.serialNumber, other.serialNumber)
            && Objects.equal(this.durationSeconds, other.durationSeconds)
            && Objects.equal(this.tokenCode, other.tokenCode);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String toString() {
      return Objects.toStringHelper(this).omitNullValues().add("serialNumber", serialNumber)
            .add("durationSeconds", durationSeconds).add("tokenCode", tokenCode).toString();
   }
}
