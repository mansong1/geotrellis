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

import monocle.macros.Lenses
import io.circe._
import io.circe.generic.semiauto._

@Lenses("_")
case class GeoTiffTags(
  modelTiePoints: Option[Array[(Pixel3D, Pixel3D)]] = None,
  modelTransformation: Option[Array[Array[Double]]] = None,
  modelPixelScale: Option[(Double, Double, Double)] = None,
  geoKeyDirectory: Option[GeoKeyDirectory] = None,
  doubles: Option[Array[Double]] = None,
  asciis: Option[String] = None,
  metadata: Option[String] = None,
  gdalInternalNoData: Option[Double] = None
)

object GeoTiffTags {
  implicit val geotiffTagsDecoder: Decoder[GeoTiffTags] = deriveDecoder[GeoTiffTags]
  implicit val geotiffTagsEncoder: Encoder[GeoTiffTags] = deriveEncoder[GeoTiffTags]
}