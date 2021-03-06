/*
 * Copyright 2016 Azavea
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package geotrellis.raster.io.geotiff

import geotrellis.raster._



class Float64UserDefinedNoDataGeoTiffSegment(bytes: Array[Byte], val userDefinedDoubleNoDataValue: Double)
    extends Float64GeoTiffSegment(bytes)
       with UserDefinedDoubleNoDataConversions {
  def getInt(i: Int): Int = udd2i(get(i))
  def getDouble(i: Int): Double = get(i)

  protected def intToDoubleOut(v: Int): Double = i2d(v)
  protected def doubleToDoubleOut(v: Double): Double = v
}
