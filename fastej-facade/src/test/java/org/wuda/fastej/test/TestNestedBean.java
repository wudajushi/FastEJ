package org.wuda.fastej.test;

import org.wuda.fastej.annotation.ExcelBean;
import org.wuda.fastej.annotation.ExcelField;
import org.wuda.fastej.annotation.ExcelNestedBean;
import org.wuda.fastej.annotation.ExcelType;

/**
 * The type Test nested bean.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-27 09:27:46
 */
@ExcelBean(inputType = ExcelType.XSSF, outputType = ExcelType.XSSF)
public class TestNestedBean {
    /**
     * The Name.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:46
     */
    @ExcelField(columnName = "姓名")
    private String name;
    /**
     * The Sex.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:46
     */
    @ExcelField(columnName = "性别")
    private String sex;
    /**
     * The Is leader.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:46
     */
    @ExcelField(columnName = "是否")
    private boolean leader;
    /**
     * The Index.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:46
     */
    @ExcelField(columnName = "索引")
    private Integer index;
    /**
     * The Nested bean.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:46
     */
    @ExcelNestedBean(columnName = "嵌套bean")
    private NestedBean nestedBean;
    @ExcelNestedBean(columnName = "最深嵌套bean")
    private SNestedBean sNestedBean;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public boolean isLeader() {
        return leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NestedBean getNestedBean() {
        return nestedBean;
    }

    public void setNestedBean(NestedBean nestedBean) {
        this.nestedBean = nestedBean;
    }

    public SNestedBean getsNestedBean() {
        return sNestedBean;
    }

    public void setsNestedBean(SNestedBean sNestedBean) {
        this.sNestedBean = sNestedBean;
    }

    private static class SNestedBean {
        @ExcelNestedBean(columnName = "嵌套Bean第一层")
        private SSNestedBean ssNestedBean;

        public SSNestedBean getSsNestedBean() {
            return ssNestedBean;
        }

        public void setSsNestedBean(SSNestedBean ssNestedBean) {
            this.ssNestedBean = ssNestedBean;
        }

        private static class SSNestedBean {
            @ExcelNestedBean(columnName = "嵌套Bean第二层")
            private SSSNestedBean sssNestedBean;
            @ExcelNestedBean(columnName = "嵌套内部类bean第二层")
            private SSSNestedBean.SSSSNestedBean ssssNestedBean;

            public SSSNestedBean.SSSSNestedBean getSsssNestedBean() {
                return ssssNestedBean;
            }

            public void setSsssNestedBean(SSSNestedBean.SSSSNestedBean ssssNestedBean) {
                this.ssssNestedBean = ssssNestedBean;
            }

            public SSSNestedBean getSssNestedBean() {
                return sssNestedBean;
            }

            public void setSssNestedBean(SSSNestedBean sssNestedBean) {
                this.sssNestedBean = sssNestedBean;
            }

            private static class SSSNestedBean {
                @ExcelNestedBean(columnName = "嵌套Bean第三层")
                private SSSSNestedBean ssssNestedBean;

                public SSSSNestedBean getSsssNestedBean() {
                    return ssssNestedBean;
                }

                public void setSsssNestedBean(SSSSNestedBean ssssNestedBean) {
                    this.ssssNestedBean = ssssNestedBean;
                }

                private static class SSSSNestedBean {
                    @ExcelField(columnName = "最深Bean - name")
                    private String name;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            }
        }
    }

    /**
     * The type Nested bean.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:46
     */
    private static class NestedBean {
        /**
         * The Age.
         *
         * @author :<a href="mailto:450783043@qq.com">悟达</a>
         * @date :2016-07-27 09:27:46
         */
        @ExcelField(columnName = "第一层年龄")
        private String age;
        /**
         * The Aaa.
         *
         * @author :<a href="mailto:450783043@qq.com">悟达</a>
         * @date :2016-07-27 09:27:46
         */
        @ExcelField(columnName = "第一层aaa")
        private String aaa;
        /**
         * The Is ggg.
         *
         * @author :<a href="mailto:450783043@qq.com">悟达</a>
         * @date :2016-07-27 09:27:46
         */
        @ExcelField(columnName = "第一层isGGG")
        private boolean ggg;
        @ExcelNestedBean(columnName = "第二层Bean")
        private NNestedBean nNestedBean;
        @ExcelNestedBean(columnName = "第二层内部类Bean")
        private NNestedBean.NNNestedBean nnNestedBean;

        public String getAaa() {
            return aaa;
        }

        public void setAaa(String aaa) {
            this.aaa = aaa;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public boolean isGgg() {
            return ggg;
        }

        public void setGgg(boolean ggg) {
            this.ggg = ggg;
        }

        public NNestedBean.NNNestedBean getNnNestedBean() {
            return nnNestedBean;
        }

        public void setNnNestedBean(NNestedBean.NNNestedBean nnNestedBean) {
            this.nnNestedBean = nnNestedBean;
        }

        public NNestedBean getnNestedBean() {
            return nNestedBean;
        }

        public void setnNestedBean(NNestedBean nNestedBean) {
            this.nNestedBean = nNestedBean;
        }

        private static class NNestedBean {
            @ExcelField(columnName = "第二层aaa")
            private String aaa;
            @ExcelField(columnName = "第二层bbb")
            private String bbb;
            @ExcelField(columnName = "第二层ccc")
            private boolean ccc;

            @ExcelNestedBean(columnName = "第三层Bean")
            private NNNestedBean nnNestedBean;

            public String getAaa() {
                return aaa;
            }

            public void setAaa(String aaa) {
                this.aaa = aaa;
            }

            public String getBbb() {
                return bbb;
            }

            public void setBbb(String bbb) {
                this.bbb = bbb;
            }

            public boolean isCcc() {
                return ccc;
            }

            public void setCcc(boolean ccc) {
                this.ccc = ccc;
            }

            public NNNestedBean getNnNestedBean() {
                return nnNestedBean;
            }

            public void setNnNestedBean(NNNestedBean nnNestedBean) {
                this.nnNestedBean = nnNestedBean;
            }

            private static class NNNestedBean {
                @ExcelField(columnName = "最深层nnnindex")
                private Integer index;
                @ExcelField(columnName = "最深层name")
                private String nestedName;

                public Integer getIndex() {
                    return index;
                }

                public void setIndex(Integer index) {
                    this.index = index;
                }

                public String getNestedName() {
                    return nestedName;
                }

                public void setNestedName(String nestedName) {
                    this.nestedName = nestedName;
                }
            }
        }
    }


}
