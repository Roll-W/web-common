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

package tech.rollw.common.web.page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author RollW
 */
@SuppressWarnings({"unused", "ClassCanBeRecord"})
public class Page<T> implements Pageable {
    /**
     * Current page number.
     */
    private final int page;
    /**
     * Current page size.
     */
    private final int size;
    /**
     * Total number of items.
     */
    private final long total;
    private final List<T> data;

    public Page(int page, int size, long total, List<T> data) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.data = data;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getSize() {
        return size;
    }

    public long getTotal() {
        return total;
    }

    public List<T> getData() {
        return data;
    }

    public Stream<T> stream() {
        return data.stream();
    }

    public <R> Page<R> transform(Function<T, R> mapper) {
        return new Page<>(
                page, size,
                total,
                data.stream().map(mapper).toList()
        );
    }


    public static <T> Page<T> of(int page, int size,
                                 int total, List<T> data) {
        return new Page<>(page, size, total, data);
    }

    public static <T> Page<T> of(Pageable pageable,
                                 long total, List<T> data) {
        return new Page<>(
                pageable.getPage(),
                pageable.getSize(),
                total, data
        );
    }


    public static <T> Page<T> of(Page<?> raw,
                                 Stream<T> data) {
        return new Page<>(
                raw.getPage(), raw.getSize(),
                raw.getTotal(), data.toList()
        );
    }

    public static <T> Page<T> of() {
        return (Page<T>) PAGE;
    }

    private static final Page<?> PAGE = new Page<>(0, 0, 0, List.of());
}
