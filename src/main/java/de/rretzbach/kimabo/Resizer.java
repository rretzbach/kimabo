package de.rretzbach.kimabo;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: rretzbach
 * Date: 11.02.12
 * Time: 09:35
 * To change this template use File | Settings | File Templates.
 */
public interface Resizer<S, T> {
    void addAll(Collection<ResizeTask<S, T>> tasks);

    void execute();
}
