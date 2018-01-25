package xyz.chanjkf.utils.page;

/**
 * Created by yi on 2017/7/31.
 */
public enum ConditionType {
    eq,             //==
    ne,             //!=
    gt,             //>
    lt,             //<
    ge,             //>=
    le,             //<=
    like,           //like 区分大小写模糊匹配查询
    ilike,           //ilike 不区分大小写模糊匹配查询
    isNull,         //
    isNotNull,
    in,
    between
}
