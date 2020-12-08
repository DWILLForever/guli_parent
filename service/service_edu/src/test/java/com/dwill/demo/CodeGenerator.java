package com.dwill.demo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @author
 * @since 2018/12/13
 */
public class CodeGenerator {

    @Test
    public void run() {

        /**
         * 代码生成器需要更改的地方，
         * 1.输出目录的路径，
         * 2.还有主键策略是否是字符串
         * 3.数据库配置
         * 4.包设置
         * 5.表名
         */
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
        //输出目录，建议写绝对路径（需要修改）
        gc.setOutputDir("D:\\IDEA\\guli_parent\\service\\service_edu" + "/src/main/java");
        gc.setAuthor("dwill");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService");	//去掉Service接口的首字母I
//        gc.setIdType(IdType.ID_WORKER); //主键策略（需要修改）
        gc.setIdType(IdType.ID_WORKER_STR); //主键策略，是字符串时使用
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3307/guli?serverTimezone=UTC");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("1234");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置（需要修改）,将会生成以下内容
        PackageConfig pc = new PackageConfig();
        //包com.dwill.edu
        pc.setModuleName("edu"); //模块名
        pc.setParent("com.dwill");
        //子包com.dwill.edu.controller
        pc.setController("controller");
        //子包com.dwill.edu.entity
        pc.setEntity("entity");
        //子包com.dwill.edu.service
        pc.setService("service");
        //子包com.dwill.edu.mapper
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        //表名，多个表用逗号隔开（需要修改）
        strategy.setInclude("edu_course","edu_course_description","edu_chapter");
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);


        // 6、执行
        mpg.execute();
    }
}
