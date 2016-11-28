package com.example.ede67167;

import java.io.InputStream;
import java.util.List;

/**
 * Created by EDE67167 on 2016-01-26.
 */
public class Event
{
    /** 列表加载事件 */
    public static class ItemListEvent<T>
    {
        private List<T> items;

        public ItemListEvent(List<T> items)
        {
            this.items = items;
        }

        public List<T> getItems()
        {
            return items;
        }
    }

    public static class TypeSelectInfo
    {
        private int mGroup;
        private int mChild;
        public TypeSelectInfo(int group,int child)
        {
            mGroup=group;
            mChild=child;
        }
        public int getGroup()
        {
            return mGroup;
        }
        public  int getChild()
        {
            return mChild;
        }

    }
    public static class PictureInputStream
    {
        private InputStream mPicInputStream;
        private int mIndex;
        public PictureInputStream(int index, InputStream picInputStream)
        {
            mIndex=index;
            mPicInputStream=picInputStream;
        }
        public InputStream getPictureInputStream()
        {
            return mPicInputStream;
        }
        public int getPictureIndex()
        {
            return mIndex;
        }
    }

}