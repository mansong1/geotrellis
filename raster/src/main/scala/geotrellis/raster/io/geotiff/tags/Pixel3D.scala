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

package geotrellis.raster.io.geotiff.tags

import io.circe.generic.JsonCodec

object Pixel3D {

  def fromArray(v: Array[Double]): Pixel3D =
    if (v.size == 3) Pixel3D(v(0), v(1), v(2))
    else throw new IllegalArgumentException(
      "3D pixel needs vector with size 3 (x, y ,z)"
    )

}

@JsonCodec
case class Pixel3D(x: Double, y: Double, z: Double)
