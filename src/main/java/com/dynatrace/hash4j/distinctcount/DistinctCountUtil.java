/*
 * Copyright 2022 Dynatrace LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dynatrace.hash4j.distinctcount;

class DistinctCountUtil {

  private DistinctCountUtil() {}
  // visible for testing

  static boolean isUnsignedPowerOfTwo(int x) {
    return (x & (x - 1)) == 0;
  }

  static void checkPrecisionParameter(int p, int minP, int maxP) {
    if (p < minP || p > maxP) {
      throw new IllegalArgumentException("illegal precision parameter");
    }
  }
}