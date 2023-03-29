/*
 * Copyright (C) 2020 The ORT Project Authors (see <https://github.com/oss-review-toolkit/ort/blob/main/NOTICE>)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * License-Filename: LICENSE
 */

package org.ossreviewtoolkit.plugins.packagemanagers.carthage

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

import org.ossreviewtoolkit.analyzer.managers.resolveSingleProject
import org.ossreviewtoolkit.model.config.AnalyzerConfiguration
import org.ossreviewtoolkit.model.config.RepositoryConfiguration
import org.ossreviewtoolkit.utils.test.USER_DIR
import org.ossreviewtoolkit.utils.test.getAssetFile
import org.ossreviewtoolkit.utils.test.patchExpectedResult2
import org.ossreviewtoolkit.utils.test.toYaml

class CarthageFunTest : StringSpec() {
    init {
        "Project dependencies are detected correctly" {
            val definitionFile = getAssetFile("projects/synthetic/carthage/Cartfile.resolved")
            val expectedResultFile = getAssetFile("projects/synthetic/carthage-expected-output.yml")

            val result = createCarthage().resolveSingleProject(definitionFile)

            result.toYaml() shouldBe patchExpectedResult2(expectedResultFile, definitionFile)
        }
    }

    private fun createCarthage() =
        Carthage("Carthage", USER_DIR, AnalyzerConfiguration(), RepositoryConfiguration())
}