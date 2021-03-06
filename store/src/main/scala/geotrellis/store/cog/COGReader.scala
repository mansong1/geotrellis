/*
 * Copyright 2019 Azavea
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

package geotrellis.store.cog

import geotrellis.store.Reader
import geotrellis.raster.Tile


trait COGReader[K, V] extends Reader[K, V] {
  def readSubsetBands(key: K, bands: Int*)(implicit d: DummyImplicit): Array[Option[Tile]] =
    readSubsetBands(key, bands.toSeq)

  def readSubsetBands(key: K, bands: Seq[Int]): Array[Option[Tile]]
}
