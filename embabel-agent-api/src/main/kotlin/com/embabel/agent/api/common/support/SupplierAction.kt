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
package com.embabel.agent.api.common.support

import com.embabel.agent.api.common.Transformation
import com.embabel.agent.core.ActionQos
import com.embabel.agent.core.IoBinding
import com.embabel.agent.core.ToolGroupRequirement
import com.embabel.common.core.types.ZeroToOne

/**
 * Action that has no input preconditions, but produces an output
 */
class SupplierAction<O>(
    name: String,
    description: String = name,
    pre: List<String> = emptyList(),
    post: List<String> = emptyList(),
    cost: ZeroToOne = 0.0,
    value: ZeroToOne = 0.0,
    canRerun: Boolean = false,
    qos: ActionQos = ActionQos(),
    outputClass: Class<O>,
    outputVarName: String? = IoBinding.Companion.DEFAULT_BINDING,
    referencedInputProperties: Set<String>? = null,
    toolGroups: Set<ToolGroupRequirement>,
    block: Transformation<Unit, O>,
) : TransformationAction<Unit, O>(
    name = name,
    description = description,
    pre = pre,
    post = post,
    cost = cost,
    value = value,
    canRerun = canRerun,
    qos = qos,
    inputClass = Unit::class.java,
    outputClass = outputClass,
    outputVarName = outputVarName,
    referencedInputProperties = referencedInputProperties,
    toolGroups = toolGroups,
    block = block
)
