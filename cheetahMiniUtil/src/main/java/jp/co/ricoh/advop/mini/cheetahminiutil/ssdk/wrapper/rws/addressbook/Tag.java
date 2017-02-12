/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Utils;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.WritableElement;

public class Tag extends WritableElement {

    private static final String KEY_TAG_ID          = "tagId";
    private static final String KEY_KEY_DISPLAY     = "keyDisplay";
    private static final String KEY_ENTRY_NUM       = "entryNum";
    private static final String KEY_TAGS_ENTRY_LIST = "tagsEntryList";

    Tag(Map<String, Object> values) {
        super(values);
    }

    /*
     * tagId (Int)
     */
    public Integer getTagId() {
        return getNumberValue(KEY_TAG_ID);
    }
    public void setTagId(Integer value) {
        setNumberValue(KEY_TAG_ID, value);
    }
    public Integer removeTagId() {
        return removeNumberValue(KEY_TAG_ID);
    }

    /*
     * keyDisplay (String)
     */
    public String getKeyDisplay() {
        return getStringValue(KEY_KEY_DISPLAY);
    }
    public void setKeyDisplay(String value) {
        setStringValue(KEY_KEY_DISPLAY, value);
    }
    public String removeKeyDisplay() {
        return removeStringValue(KEY_KEY_DISPLAY);
    }

    /*
     * entryNum (Int)
     */
    public Integer getEntryNum() {
        return getNumberValue(KEY_ENTRY_NUM);
    }
    public void setEntryNum(Integer value) {
        setNumberValue(KEY_ENTRY_NUM, value);
    }
    public Integer removeEntryNum() {
        return removeNumberValue(KEY_ENTRY_NUM);
    }

    /*
     * tagsEntryList (Object)
     */
    public TagsEntryList getTagsEntryList() {
        Map<String, Object> value = getObjectValue(KEY_TAGS_ENTRY_LIST);
        if (value == null) {
            value = Utils.createElementMap();
            setObjectValue(KEY_TAGS_ENTRY_LIST, value);
        }
        return new TagsEntryList(value);
    }
    public TagsEntryList removeTagsEntryList() {
        Map<String, Object> value = removeObjectValue(KEY_TAGS_ENTRY_LIST);
        if (value == null) {
            return null;
        }
        return new TagsEntryList(value);
    }


    public static class TagsEntryList extends WritableElement {

        private static final String KEY_TAG_PRIORITY_1  = "tagPriority_1";
        private static final String KEY_TAG_PRIORITY_2  = "tagPriority_2";
        private static final String KEY_TAG_PRIORITY_3  = "tagPriority_3";
        private static final String KEY_TAG_PRIORITY_4  = "tagPriority_4";
        private static final String KEY_TAG_PRIORITY_5  = "tagPriority_5";
        private static final String KEY_TAG_PRIORITY_6  = "tagPriority_6";
        private static final String KEY_TAG_PRIORITY_7  = "tagPriority_7";
        private static final String KEY_TAG_PRIORITY_8  = "tagPriority_8";
        private static final String KEY_TAG_PRIORITY_9  = "tagPriority_9";
        private static final String KEY_TAG_PRIORITY_10 = "tagPriority_10";

        TagsEntryList(Map<String, Object> value) {
            super(value);
        }

        /*
         * tagPriority_1 (Array[String])
         */
        public List<String> getTagPriority1() {
            return getArrayValue(KEY_TAG_PRIORITY_1);
        }
        public void setTagPriority1(List<String> values) {
            setArrayValue(KEY_TAG_PRIORITY_1, values);
        }
        public List<String> removeTagPriority1() {
            return removeArrayValue(KEY_TAG_PRIORITY_1);
        }

        /*
         * tagPriority_2 (Array[String])
         */
        public List<String> getTagPriority2() {
            return getArrayValue(KEY_TAG_PRIORITY_2);
        }
        public void setTagPriority2(List<String> values) {
            setArrayValue(KEY_TAG_PRIORITY_2, values);
        }
        public List<String> removeTagPriority2() {
            return removeArrayValue(KEY_TAG_PRIORITY_2);
        }

        /*
         * tagPriority_3 (Array[String])
         */
        public List<String> getTagPriority3() {
            return getArrayValue(KEY_TAG_PRIORITY_3);
        }
        public void setTagPriority3(List<String> values) {
            setArrayValue(KEY_TAG_PRIORITY_3, values);
        }
        public List<String> removeTagPriority3() {
            return removeArrayValue(KEY_TAG_PRIORITY_3);
        }

        /*
         * tagPriority_4 (Array[String])
         */
        public List<String> getTagPriority4() {
            return getArrayValue(KEY_TAG_PRIORITY_4);
        }
        public void setTagPriority4(List<String> values) {
            setArrayValue(KEY_TAG_PRIORITY_4, values);
        }
        public List<String> removeTagPriority4() {
            return removeArrayValue(KEY_TAG_PRIORITY_4);
        }

        /*
         * tagPriority_5 (Array[String])
         */
        public List<String> getTagPriority5() {
            return getArrayValue(KEY_TAG_PRIORITY_5);
        }
        public void setTagPriority5(List<String> values) {
            setArrayValue(KEY_TAG_PRIORITY_5, values);
        }
        public List<String> removeTagPriority5() {
            return removeArrayValue(KEY_TAG_PRIORITY_5);
        }

        /*
         * tagPriority_6 (Array[String])
         */
        public List<String> getTagPriority6() {
            return getArrayValue(KEY_TAG_PRIORITY_6);
        }
        public void setTagPriority6(List<String> values) {
            setArrayValue(KEY_TAG_PRIORITY_6, values);
        }
        public List<String> removeTagPriority6() {
            return removeArrayValue(KEY_TAG_PRIORITY_6);
        }

        /*
         * tagPriority_7 (Array[String])
         */
        public List<String> getTagPriority7() {
            return getArrayValue(KEY_TAG_PRIORITY_7);
        }
        public void setTagPriority7(List<String> values) {
            setArrayValue(KEY_TAG_PRIORITY_7, values);
        }
        public List<String> removeTagPriority7() {
            return removeArrayValue(KEY_TAG_PRIORITY_7);
        }

        /*
         * tagPriority_8 (Array[String])
         */
        public List<String> getTagPriority8() {
            return getArrayValue(KEY_TAG_PRIORITY_8);
        }
        public void setTagPriority8(List<String> values) {
            setArrayValue(KEY_TAG_PRIORITY_8, values);
        }
        public List<String> removeTagPriority8() {
            return removeArrayValue(KEY_TAG_PRIORITY_8);
        }

        /*
         * tagPriority_9 (Array[String])
         */
        public List<String> getTagPriority9() {
            return getArrayValue(KEY_TAG_PRIORITY_9);
        }
        public void setTagPriority9(List<String> values) {
            setArrayValue(KEY_TAG_PRIORITY_9, values);
        }
        public List<String> removeTagPriority9() {
            return removeArrayValue(KEY_TAG_PRIORITY_9);
        }

        /*
         * tagPriority_10 (Array[String])
         */
        public List<String> getTagPriority10() {
            return getArrayValue(KEY_TAG_PRIORITY_10);
        }
        public void setTagPriority10(List<String> values) {
            setArrayValue(KEY_TAG_PRIORITY_10, values);
        }
        public List<String> removeTagPriority10() {
            return removeArrayValue(KEY_TAG_PRIORITY_10);
        }

    }

}
