//package com.cn.cms.generator;
//
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.rules.DbType;
//
///**
// * 代码生成器
// */
//public class TyqxCmsGenerator {
//
//    // 根据命名规范，只修改此常量值即可
//    private static String MODULE = "cms";
//    private static String TABLE_PREFIX = "sys_";
//    private static String PARENT_PACKAGE_NAME = "com.cn";
//
//    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    private static String JDBC_URL = "jdbc:mysql://47.105.162.183:3306/cms?useUnicode=true&amp;characterEncoding=UTF-8&amp;tinyInt1isBit=false";
//    private static String JDBC_USERNAME = "root";
//    private static String JDBC_PASSWORD = "728728";
//
//    private static String OUTPUT_DIR = "D:\\workspace\\cms\\src\\main\\java";
//
//
//    /**
//     * 自动代码生成
//     * @param args
//     */
//    public static void main(String[] args) {
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setDbType(DbType.MYSQL);
//        dsc.setDriverName(JDBC_DRIVER);
//        dsc.setUsername(JDBC_USERNAME);
//        dsc.setPassword(JDBC_PASSWORD);
//        dsc.setUrl(JDBC_URL);
//
//        PackageConfig pc = new PackageConfig();
//        pc.setParent(PARENT_PACKAGE_NAME);
//        pc.setModuleName(MODULE);
//
//        DefaultGenerator defaultGenerator = new DefaultGenerator();
//        defaultGenerator.setDataSource(dsc);
//        defaultGenerator.setPackageInfo(pc);
//        defaultGenerator.setIncludeTables(TABLE_PREFIX,"sys_user");
//
//        // 生成API
//        defaultGenerator.setOutputDir(OUTPUT_DIR);
//        defaultGenerator.createClass(
//                "entity"
//                ,"service"
//                , "mapper"
//        );
////        defaultGenerator.setPackageInfo(null);
////        defaultGenerator.setOutputDir(OUTPUT_MAPPER_DIR);
////        defaultGenerator.createClass(
////                "Mapper"
////        );
////        // 生成服务
////        defaultGenerator.setOutputDir(OUTPUT_DIR);
////        defaultGenerator.createClass(
////                "Mapper"
////                , "mapper"
////                , "serviceImpl"
////        );
////        // 生成Controller
////        defaultGenerator.setOutputDir(OUTPUT_DIR);
////        defaultGenerator.createClass("controller");
//    }
//
//}
