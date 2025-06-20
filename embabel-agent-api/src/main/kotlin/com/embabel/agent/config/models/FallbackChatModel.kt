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
package com.embabel.agent.config.models

import com.embabel.common.ai.model.Llm
import org.slf4j.LoggerFactory
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt

/**
 * Chat model that falls back to another model if the first one fails.
 */
class FallbackChatModel(
    private val model: ChatModel,
    private val fallback: ChatModel,
    private val whenError: (t: Throwable) -> Boolean,
) : ChatModel {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun call(prompt: Prompt?): ChatResponse? {
        return try {
            model.call(prompt)
        } catch (t: Throwable) {
            if (whenError(t)) {
                logger.info("Flipping to fallback model: {}", t.message)
                fallback.call(prompt)
            } else {
                throw t
            }
        }
    }
}

fun ChatModel.withFallback(
    model: ChatModel,
    whenError: (t: Throwable) -> Boolean,
): ChatModel {
    return FallbackChatModel(this, model, whenError)
}

fun Llm.withFallback(
    llm: Llm?,
    whenError: (t: Throwable) -> Boolean,
): Llm {
    if (llm == null) {
        return this
    }
    return Llm(
        name = this.name,
        model = this.model.withFallback(llm.model, whenError),
        optionsConverter = llm.optionsConverter,
        provider = this.provider,
        knowledgeCutoffDate = this.knowledgeCutoffDate,
    )
}
