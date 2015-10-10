package com.appleframework.monitor.service;

import com.appleframework.monitor.model.Alert;

/**
 * author: Hill.Hu
 */
public interface AlertListener {

    public void notify(Alert alert);
}
