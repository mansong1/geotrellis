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
import geotrellis.raster.io.geotiff.compression._


class UByteGeoTiffMultibandTile(
  compressedBytes: SegmentBytes,
  decompressor: Decompressor,
  segmentLayout: GeoTiffSegmentLayout,
  compression: Compression,
  bandCount: Int,
  val cellType: UByteCells with NoDataHandling,
  overviews: List[UByteGeoTiffMultibandTile] = Nil
) extends GeoTiffMultibandTile(compressedBytes, decompressor, segmentLayout, compression, bandCount, overviews)
    with UByteGeoTiffSegmentCollection {

  val noDataValue: Option[Int] = cellType match {
    case UByteCellType => None
    case UByteConstantNoDataCellType => Some(0)
    case UByteUserDefinedNoDataCellType(nd) => Some(nd)
  }

  protected def createSegmentCombiner(targetSize: Int): SegmentCombiner =
    new SegmentCombiner(bandCount) {
      private val arr = Array.ofDim[Byte](targetSize)

      def set(targetIndex: Int, v: Int): Unit = {
        arr(targetIndex) = i2b(v)
      }

      def setDouble(targetIndex: Int, v: Double): Unit = {
        arr(targetIndex) = d2b(v)
      }

      def getBytes(): Array[Byte] = arr
    }

  def withNoData(noDataValue: Option[Double]): UByteGeoTiffMultibandTile =
    new UByteGeoTiffMultibandTile(compressedBytes, decompressor, segmentLayout, compression, bandCount, cellType.withNoData(noDataValue), overviews.map(_.withNoData(noDataValue)))

  def interpretAs(newCellType: CellType): GeoTiffMultibandTile = {
    newCellType match {
      case dt: UByteCells with NoDataHandling =>
        new UByteGeoTiffMultibandTile(compressedBytes, decompressor, segmentLayout, compression, bandCount, dt, overviews.map(_.interpretAs(newCellType)).collect { case gt: UByteGeoTiffMultibandTile => gt })
      case _ =>
        withNoData(None).convert(newCellType)
    }
  }

}
