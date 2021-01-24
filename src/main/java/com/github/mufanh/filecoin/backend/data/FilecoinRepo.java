package com.github.mufanh.filecoin.backend.data;

import com.github.mufanh.filecoin.backend.spider.FilecoinSpiderData;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xinquan.huangxq
 */
public final class FilecoinRepo {

    private static final FilecoinRepo INSTANCE = new FilecoinRepo();

    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    private FilecoinSpiderData spiderData;

    public FilecoinSpiderData getSpiderData() {
        rwl.readLock().lock();
        try {
            return spiderData;
        } finally {
            rwl.readLock().unlock();
        }
    }

    public void setSpiderData(FilecoinSpiderData spiderData) {
        rwl.writeLock().lock();
        try {
            this.spiderData = spiderData;
        } finally {
            rwl.writeLock().unlock();
        }
    }

    private FilecoinRepo() {
    }

    public static FilecoinRepo getInstance() {
        return INSTANCE;
    }
}
