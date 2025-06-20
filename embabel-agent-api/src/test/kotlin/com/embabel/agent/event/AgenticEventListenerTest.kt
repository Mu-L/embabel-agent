/*
 * Copyright 2024-2025 Embabel Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.embabel.agent.event

import com.embabel.agent.api.dsl.evenMoreEvilWizard
import com.embabel.agent.testing.IntegrationTestUtils.dummyAgentPlatform
import io.mockk.mockk
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class AgenticEventListenerTest {

    @Nested
    inner class MulticastAgenticEventListenerTest {

        @Test
        fun `can recover from errors in platform event`() {
            val l1 = object : AgenticEventListener {
                override fun onPlatformEvent(event: AgentPlatformEvent) {
                    TODO()
                }
            }
            val ml = AgenticEventListener.of(l1)
            ml.onPlatformEvent(
                AgentDeploymentEvent(
                    agentPlatform = dummyAgentPlatform(),
                    agent = evenMoreEvilWizard(),
                )
            )
        }

        @Test
        fun `can recover from errors in process event`() {
            val l1 = object : AgenticEventListener {
                override fun onProcessEvent(event: AgentProcessEvent) {
                    TODO()
                }
            }
            val ml = AgenticEventListener.of(l1)
            ml.onProcessEvent(
                AgentProcessCreationEvent(
                    agentProcess = mockk(relaxed = true),
                )
            )
        }
    }

}
