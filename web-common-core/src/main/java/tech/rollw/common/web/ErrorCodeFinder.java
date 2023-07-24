/*
 * Copyright (C) 2023 RollW
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.rollw.common.web;

import space.lingu.Nullable;

import java.util.List;

/**
 * @author RollW
 */
public interface ErrorCodeFinder {
    /**
     * Find the error code of the exception.
     *
     * @param e the exception
     * @return the error code
     */
    default ErrorCode fromThrowable(Throwable e) {
        return fromThrowable(e, CommonErrorCode.ERROR_UNKNOWN);
    }

    ErrorCode fromThrowable(Throwable e, ErrorCode defaultErrorCode);

    ErrorCode findErrorCode(String codeValue);

    List<ErrorCode> listErrorCodes();

    @Nullable
    static <T extends ErrorCode> T from(T[] codes, String codeValue) {
        if (codes == null || codeValue == null) {
            return null;
        }
        for (T errorCode : codes) {
            if (errorCode.getCode().equals(codeValue)) {
                return errorCode;
            }
        }
        return null;
    }
}
