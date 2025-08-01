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
package com.embabel.agent.shell.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "embabel.shell")
data class ShellProperties(
    val lineLength: Int = 140,
    val chat: ChatConfig = ChatConfig(),
) {
    /**
     * Configuration for the chat session
     * @param confirmGoals Whether to confirm goals with the user before proceeding
     * @param bindConversation Whether to bind the conversation to the chat session
     */
    data class ChatConfig(
        val confirmGoals: Boolean = true,
        val bindConversation: Boolean = false,
    )
}
