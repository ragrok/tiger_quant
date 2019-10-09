package com.tigerbrokers.quant.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

/**
 * 对象批量复制
 *
 * @Author alan
 */
@Slf4j
public class BatchCopyUtil {

    /**
     * 批量复制对象属性值
     *
     * @param sources 源
     * @return
     * @Author alan
     */
    public static <T> List<T> copyBachProperties(List sources, Class<T> beanClass) {
        List<T> targetList = new ArrayList<>(sources.size());
        Iterator iterator = sources.iterator();
        while (iterator.hasNext()) {
            targetList.add(copySingleProperties(iterator.next(), beanClass));
        }
        return targetList;
    }

    /**
     * 复制hutools中的分页dto对象到vo
     *
     * @param page
     * @param clazz
     * @return
     */
//    public static <T> Page<T> copyPage(IPage<T> page, Class clazz) {
//        return (Page<T>) page.setRecords(BatchCopyUtil.copyBachProperties(page.getRecords(), clazz));
//    }

    /**
     * 复制listMap
     *
     * @param page
     * @return
     */
//    public static <T> Page<T> copyMapRecordPage(IPage page, Class<T> clazz) {
//        List<T> newRecord = new ArrayList<>(page.getRecords().size());
//        for (int i = 0; i < page.getRecords().size(); i++) {
//            Map map = (Map) page.getRecords().get(i);
//            map = removeKeyPrefix(map, "f_");
//            map = MapUtil.toCamelCaseMap(map);
//            newRecord.add(copySingleProperties(map, clazz));
//        }
//        page.setRecords(newRecord);
//        return (Page) page;
//    }

    /**
     * 删除map前缀
     *
     * @param map
     * @param fix
     * @return
     */
    private static Map removeKeyPrefix(Map map, String fix) {
        Map newMap = new HashMap(map.size());
        if (map == null || map.size() == 0) {
            return map;
        }
        map.forEach((k, v) -> {
            if (v instanceof Timestamp) {
                v = ((Timestamp) v).toLocalDateTime();
            } else if (v instanceof Time) {
                v = ((Time) v).toLocalTime();
            }

            if (k.toString().startsWith(fix)) {
                newMap.put(k.toString().substring(fix.length()), v);
            } else {
                newMap.put(k, v);
            }
        });
        return newMap;
    }


    /**
     * 批量复制page 对象
     *
     * @param pageInfo 源
     * @return
     * @Author alan
     */
//    public static <T> PageInfo<T> copyPageInfo(PageInfo pageInfo, Class<T> beanClass) {
//        PageInfo<T> info = copySingleProperties(pageInfo, PageInfo.class);
//        List<T> list = copyBachProperties(pageInfo.getList(), beanClass);
//        info.setList(list);
//        return info;
//    }

    /**
     * 复制单个bean对象
     *
     * @param object 源
     * @return
     * @Author alan
     */
    public static <T> T copySingleProperties(Object object, Class<T> beanClass) {
        T instance = null;
        try {
            instance = beanClass.newInstance();
            BeanUtil.copyProperties(object, instance, CopyOptions.create().ignoreNullValue());
        } catch (Exception e) {
            log.error("copy bean to targetBean error", e);
        }
        return instance;
    }
}
