package com.java.teaching.entity;

import java.util.ArrayList;
import java.util.List;

public class ResearchResourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResearchResourceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDetailsIsNull() {
            addCriterion("details is null");
            return (Criteria) this;
        }

        public Criteria andDetailsIsNotNull() {
            addCriterion("details is not null");
            return (Criteria) this;
        }

        public Criteria andDetailsEqualTo(String value) {
            addCriterion("details =", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsNotEqualTo(String value) {
            addCriterion("details <>", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsGreaterThan(String value) {
            addCriterion("details >", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("details >=", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsLessThan(String value) {
            addCriterion("details <", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsLessThanOrEqualTo(String value) {
            addCriterion("details <=", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsLike(String value) {
            addCriterion("details like", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsNotLike(String value) {
            addCriterion("details not like", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsIn(List<String> values) {
            addCriterion("details in", values, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsNotIn(List<String> values) {
            addCriterion("details not in", values, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsBetween(String value1, String value2) {
            addCriterion("details between", value1, value2, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsNotBetween(String value1, String value2) {
            addCriterion("details not between", value1, value2, "details");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andPressIsNull() {
            addCriterion("press is null");
            return (Criteria) this;
        }

        public Criteria andPressIsNotNull() {
            addCriterion("press is not null");
            return (Criteria) this;
        }

        public Criteria andPressEqualTo(String value) {
            addCriterion("press =", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressNotEqualTo(String value) {
            addCriterion("press <>", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressGreaterThan(String value) {
            addCriterion("press >", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressGreaterThanOrEqualTo(String value) {
            addCriterion("press >=", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressLessThan(String value) {
            addCriterion("press <", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressLessThanOrEqualTo(String value) {
            addCriterion("press <=", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressLike(String value) {
            addCriterion("press like", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressNotLike(String value) {
            addCriterion("press not like", value, "press");
            return (Criteria) this;
        }

        public Criteria andPressIn(List<String> values) {
            addCriterion("press in", values, "press");
            return (Criteria) this;
        }

        public Criteria andPressNotIn(List<String> values) {
            addCriterion("press not in", values, "press");
            return (Criteria) this;
        }

        public Criteria andPressBetween(String value1, String value2) {
            addCriterion("press between", value1, value2, "press");
            return (Criteria) this;
        }

        public Criteria andPressNotBetween(String value1, String value2) {
            addCriterion("press not between", value1, value2, "press");
            return (Criteria) this;
        }

        public Criteria andTreatiseIsNull() {
            addCriterion("treatise is null");
            return (Criteria) this;
        }

        public Criteria andTreatiseIsNotNull() {
            addCriterion("treatise is not null");
            return (Criteria) this;
        }

        public Criteria andTreatiseEqualTo(String value) {
            addCriterion("treatise =", value, "treatise");
            return (Criteria) this;
        }

        public Criteria andTreatiseNotEqualTo(String value) {
            addCriterion("treatise <>", value, "treatise");
            return (Criteria) this;
        }

        public Criteria andTreatiseGreaterThan(String value) {
            addCriterion("treatise >", value, "treatise");
            return (Criteria) this;
        }

        public Criteria andTreatiseGreaterThanOrEqualTo(String value) {
            addCriterion("treatise >=", value, "treatise");
            return (Criteria) this;
        }

        public Criteria andTreatiseLessThan(String value) {
            addCriterion("treatise <", value, "treatise");
            return (Criteria) this;
        }

        public Criteria andTreatiseLessThanOrEqualTo(String value) {
            addCriterion("treatise <=", value, "treatise");
            return (Criteria) this;
        }

        public Criteria andTreatiseLike(String value) {
            addCriterion("treatise like", value, "treatise");
            return (Criteria) this;
        }

        public Criteria andTreatiseNotLike(String value) {
            addCriterion("treatise not like", value, "treatise");
            return (Criteria) this;
        }

        public Criteria andTreatiseIn(List<String> values) {
            addCriterion("treatise in", values, "treatise");
            return (Criteria) this;
        }

        public Criteria andTreatiseNotIn(List<String> values) {
            addCriterion("treatise not in", values, "treatise");
            return (Criteria) this;
        }

        public Criteria andTreatiseBetween(String value1, String value2) {
            addCriterion("treatise between", value1, value2, "treatise");
            return (Criteria) this;
        }

        public Criteria andTreatiseNotBetween(String value1, String value2) {
            addCriterion("treatise not between", value1, value2, "treatise");
            return (Criteria) this;
        }

        public Criteria andPatentIsNull() {
            addCriterion("patent is null");
            return (Criteria) this;
        }

        public Criteria andPatentIsNotNull() {
            addCriterion("patent is not null");
            return (Criteria) this;
        }

        public Criteria andPatentEqualTo(String value) {
            addCriterion("patent =", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentNotEqualTo(String value) {
            addCriterion("patent <>", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentGreaterThan(String value) {
            addCriterion("patent >", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentGreaterThanOrEqualTo(String value) {
            addCriterion("patent >=", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentLessThan(String value) {
            addCriterion("patent <", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentLessThanOrEqualTo(String value) {
            addCriterion("patent <=", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentLike(String value) {
            addCriterion("patent like", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentNotLike(String value) {
            addCriterion("patent not like", value, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentIn(List<String> values) {
            addCriterion("patent in", values, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentNotIn(List<String> values) {
            addCriterion("patent not in", values, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentBetween(String value1, String value2) {
            addCriterion("patent between", value1, value2, "patent");
            return (Criteria) this;
        }

        public Criteria andPatentNotBetween(String value1, String value2) {
            addCriterion("patent not between", value1, value2, "patent");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicIsNull() {
            addCriterion("vertical_topic is null");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicIsNotNull() {
            addCriterion("vertical_topic is not null");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicEqualTo(String value) {
            addCriterion("vertical_topic =", value, "verticalTopic");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicNotEqualTo(String value) {
            addCriterion("vertical_topic <>", value, "verticalTopic");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicGreaterThan(String value) {
            addCriterion("vertical_topic >", value, "verticalTopic");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicGreaterThanOrEqualTo(String value) {
            addCriterion("vertical_topic >=", value, "verticalTopic");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicLessThan(String value) {
            addCriterion("vertical_topic <", value, "verticalTopic");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicLessThanOrEqualTo(String value) {
            addCriterion("vertical_topic <=", value, "verticalTopic");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicLike(String value) {
            addCriterion("vertical_topic like", value, "verticalTopic");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicNotLike(String value) {
            addCriterion("vertical_topic not like", value, "verticalTopic");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicIn(List<String> values) {
            addCriterion("vertical_topic in", values, "verticalTopic");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicNotIn(List<String> values) {
            addCriterion("vertical_topic not in", values, "verticalTopic");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicBetween(String value1, String value2) {
            addCriterion("vertical_topic between", value1, value2, "verticalTopic");
            return (Criteria) this;
        }

        public Criteria andVerticalTopicNotBetween(String value1, String value2) {
            addCriterion("vertical_topic not between", value1, value2, "verticalTopic");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesIsNull() {
            addCriterion("horizontal_issues is null");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesIsNotNull() {
            addCriterion("horizontal_issues is not null");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesEqualTo(String value) {
            addCriterion("horizontal_issues =", value, "horizontalIssues");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesNotEqualTo(String value) {
            addCriterion("horizontal_issues <>", value, "horizontalIssues");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesGreaterThan(String value) {
            addCriterion("horizontal_issues >", value, "horizontalIssues");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesGreaterThanOrEqualTo(String value) {
            addCriterion("horizontal_issues >=", value, "horizontalIssues");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesLessThan(String value) {
            addCriterion("horizontal_issues <", value, "horizontalIssues");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesLessThanOrEqualTo(String value) {
            addCriterion("horizontal_issues <=", value, "horizontalIssues");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesLike(String value) {
            addCriterion("horizontal_issues like", value, "horizontalIssues");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesNotLike(String value) {
            addCriterion("horizontal_issues not like", value, "horizontalIssues");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesIn(List<String> values) {
            addCriterion("horizontal_issues in", values, "horizontalIssues");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesNotIn(List<String> values) {
            addCriterion("horizontal_issues not in", values, "horizontalIssues");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesBetween(String value1, String value2) {
            addCriterion("horizontal_issues between", value1, value2, "horizontalIssues");
            return (Criteria) this;
        }

        public Criteria andHorizontalIssuesNotBetween(String value1, String value2) {
            addCriterion("horizontal_issues not between", value1, value2, "horizontalIssues");
            return (Criteria) this;
        }

        public Criteria andTidIsNull() {
            addCriterion("tid is null");
            return (Criteria) this;
        }

        public Criteria andTidIsNotNull() {
            addCriterion("tid is not null");
            return (Criteria) this;
        }

        public Criteria andTidEqualTo(Integer value) {
            addCriterion("tid =", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotEqualTo(Integer value) {
            addCriterion("tid <>", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThan(Integer value) {
            addCriterion("tid >", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThanOrEqualTo(Integer value) {
            addCriterion("tid >=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThan(Integer value) {
            addCriterion("tid <", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThanOrEqualTo(Integer value) {
            addCriterion("tid <=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidIn(List<Integer> values) {
            addCriterion("tid in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotIn(List<Integer> values) {
            addCriterion("tid not in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidBetween(Integer value1, Integer value2) {
            addCriterion("tid between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotBetween(Integer value1, Integer value2) {
            addCriterion("tid not between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andPathIsNull() {
            addCriterion("path is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("path is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("path =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("path <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("path >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("path >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("path <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("path <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("path like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("path not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("path in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("path not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("path between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("path not between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andSidIsNull() {
            addCriterion("sid is null");
            return (Criteria) this;
        }

        public Criteria andSidIsNotNull() {
            addCriterion("sid is not null");
            return (Criteria) this;
        }

        public Criteria andSidEqualTo(Integer value) {
            addCriterion("sid =", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotEqualTo(Integer value) {
            addCriterion("sid <>", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThan(Integer value) {
            addCriterion("sid >", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sid >=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThan(Integer value) {
            addCriterion("sid <", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThanOrEqualTo(Integer value) {
            addCriterion("sid <=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidIn(List<Integer> values) {
            addCriterion("sid in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotIn(List<Integer> values) {
            addCriterion("sid not in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidBetween(Integer value1, Integer value2) {
            addCriterion("sid between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotBetween(Integer value1, Integer value2) {
            addCriterion("sid not between", value1, value2, "sid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}