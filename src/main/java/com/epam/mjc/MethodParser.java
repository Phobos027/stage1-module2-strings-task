package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        String signatureStr = signatureString.substring(0, signatureString.indexOf('('));
        String signatureArgs;
        signatureArgs = signatureString.substring(signatureString.indexOf('(')+1, signatureString.indexOf(')'));

        String [] signature = signatureStr.split(" ");
        String accessModifier = null;
        String returnType = signature[signature.length - 2];
        String methodName = signature[signature.length - 1];
        if (signature.length == 3) accessModifier = signature[signature.length - 3];

        List <MethodSignature.Argument> args = new ArrayList<>();
        if (!signatureArgs.isBlank()){
            String [] arguments = signatureArgs.split(", ");
            for (String argument : arguments) {
                String [] argumentPart = argument.split(" ");
                args.add(new MethodSignature.Argument(argumentPart[0], argumentPart[1]));
            }
        }

        MethodSignature methodSignature = new MethodSignature(methodName, args);
        methodSignature.setReturnType(returnType);
        methodSignature.setAccessModifier(accessModifier);
        return methodSignature;

    }
}
