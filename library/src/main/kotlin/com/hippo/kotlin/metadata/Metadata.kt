/*
 * Copyright 2018 Hippo Seven
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

package com.hippo.kotlin.metadata

import org.jetbrains.kotlin.metadata.ProtoBuf
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion
import java.io.File
import java.io.InputStream
import java.io.OutputStream

/*
 * Copyright 2018 Hippo Seven
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

class Metadata(
    val version: BinaryVersion,
    val fragment: ProtoBuf.PackageFragment
) {

  fun writeTo(file: File) = file.outputStream().use { writeTo(it) }

  fun writeTo(stream: OutputStream) {
    version.writeTo(stream)
    fragment.writeTo(stream)
  }

  companion object {
    fun parseFrom(file: File): Metadata = file.inputStream().use { parseFrom(it) }

    fun parseFrom(stream: InputStream): Metadata =
        Metadata(BinaryVersion.parseFrom(stream), ProtoBuf.PackageFragment.parseFrom(stream))
  }
}
