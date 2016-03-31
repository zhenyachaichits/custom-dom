package com.epam.task1.logic.viewer;

import java.io.OutputStream;

/**
 * Created by Zheny Chaichits on 09.12.2015.
 */
public interface Viewable {
    String DELIMETER = "____________________________________________________";

    void view(OutputStream os);
}
