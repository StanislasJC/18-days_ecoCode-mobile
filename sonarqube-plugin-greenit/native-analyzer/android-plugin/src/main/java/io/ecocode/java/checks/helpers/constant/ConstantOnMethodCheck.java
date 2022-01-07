/*
 * ecoCode SonarQube Plugin
 * Copyright (C) 2020-2021 Snapp' - Université de Pau et des Pays de l'Adour
 * mailto: contact@ecocode.io
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package io.ecocode.java.checks.helpers.constant;

import org.sonar.plugins.java.api.tree.Tree;

import java.util.Optional;

/**
 * Checks if an argument of a method is different from a given constant value.
 */
public abstract class ConstantOnMethodCheck extends IntegerArgumentValueOnMethodCheck {

    /**
     * Constructor to configure the rule on a given class and method.
     *
     * @param methodName           name of the method to check
     * @param methodOwnerType      name of the type that own the method
     * @param constantValueToCheck the constant value to check
     * @param paramPositions       the position(s) of the argument on the method to check
     */
    protected ConstantOnMethodCheck(String methodName, String methodOwnerType, int constantValueToCheck, int... paramPositions) {
        super(methodName, methodOwnerType, constantValueToCheck, paramPositions);
    }

    /**
     * In this case, we report an issue if the argument value is different from a given value.
     *
     * @param optionalConstantValue the argument value of the method as an optional value
     * @param reportTree the tree where the issue will be reported
     * @param constantValueToCheck the value to use to check the argument
     */
    @Override
    protected void checkConstantValue(Optional<Object> optionalConstantValue, Tree reportTree, int constantValueToCheck) {
        if ((optionalConstantValue.isPresent() && (((Number) optionalConstantValue.get()).intValue() != constantValueToCheck))) {
            reportIssue(reportTree, getMessage());
        }
    }
}
