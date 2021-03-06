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

package geotrellis.layer.mapalgebra.local

import geotrellis.raster.mapalgebra.local.And
import geotrellis.raster.Tile
import geotrellis.layer._
import geotrellis.util.MethodExtensions


trait AndTileCollectionMethods[K] extends MethodExtensions[Seq[(K, Tile)]] {
  /** And a constant Int value to each cell. */
  def localAnd(i: Int) =
    self.mapValues { r => And(r, i) }

  /** And a constant Int value to each cell. */
  def &(i: Int) = localAnd(i)

  /** And a constant Int value to each cell. */
  def &:(i: Int) = localAnd(i)

  /** And the values of each cell in each raster.  */
  def localAnd(other: Seq[(K, Tile)]): Seq[(K, Tile)] =
    self.combineValues(other){ And.apply }

  /** And the values of each cell in each raster. */
  def &(rs: TileLayerCollection[K]): Seq[(K, Tile)] = localAnd(rs)

  /** And the values of each cell in each raster.  */
  def localAnd(others: Traversable[Seq[(K, Tile)]]): Seq[(K, Tile)] =
    self.combineValues(others){ And.apply }

  /** And the values of each cell in each raster. */
  def &(others: Traversable[Seq[(K, Tile)]]): Seq[(K, Tile)] = localAnd(others)
}
