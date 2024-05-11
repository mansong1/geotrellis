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

package geotrellis.geotools

import geotrellis.util.MethodExtensions
import geotrellis.vector._

import org.geotools.api.feature.simple.SimpleFeature

import scala.reflect.ClassTag

trait SimpleFeatureToGeometryMethods extends MethodExtensions[SimpleFeature] {
  def toGeometry[G <: Geometry : ClassTag](): G = {
    val feature = SimpleFeatureToFeature(self)
    val ct = implicitly[ClassTag[G]]

    feature.geom match {
      case ct(g) => feature.geom.asInstanceOf[G]
      case _ => throw new Exception(s"${feature.geom} is not a ${ct.toString}")
    }
  }
}
