package com.github.mufanh.filecoin.backend.data;

import com.github.mufanh.filecoin.backend.lotus.FilecoinLotusData;
import com.github.mufanh.filecoin.backend.spider.FilecoinSpiderData;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xinquan.huangxq
 */
public final class FilecoinRepo {

    private static final FilecoinRepo INSTANCE = new FilecoinRepo();

    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    private FilecoinSpiderData spiderData;

    private FilecoinLotusData lotusData;

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


    public FilecoinLotusData getLotusData() {
        rwl.readLock().lock();
        try {
            return lotusData;
        } finally {
            rwl.readLock().unlock();
        }
    }

    public void setLotusData(FilecoinLotusData lotusData) {
        rwl.writeLock().lock();
        try {
            this.lotusData = lotusData;
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
