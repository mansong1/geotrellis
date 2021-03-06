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
case class JpegTags(
  jpegTables: Option[Array[Byte]] = None,
  jpegProc: Option[Int] = None,
  jpegInterchangeFormat: Option[Long] = None,
  jpegInterchangeFormatLength: Option[Long] = None,
  jpegRestartInterval: Option[Int] = None,
  jpegLosslessPredictors: Option[Array[Int]] = None,
  jpegPointTransforms: Option[Array[Int]] = None,
  jpegQTables: Option[Array[Long]] = None,
  jpegDCTables: Option[Array[Long]] = None,
  jpegACTables: Option[Array[Long]] = None
)

object JpegTags {
  implicit val jpegTagsEncoder: Encoder[JpegTags] = deriveEncoder[JpegTags]
  implicit val jpegTagsDecoder: Decoder[JpegTags] = deriveDecoder[JpegTags]
}