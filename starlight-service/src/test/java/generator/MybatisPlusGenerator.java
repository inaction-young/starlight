package generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @ClassName: MybatisPlusGenerator
 * @Description:
 * @Author: tangjian
 * @Date: 2021/7/6 下午4:36
 * @Version V1.0
 */
public class MybatisPlusGenerator {
    private static String au = "Tj";

    private static String driver = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "Nw6zOd=hfbhz.";
    private static String url = "jdbc:mysql://60.204.224.204:3306/%s?useUnicode=true&characterEncoding=utf8&tinyInt1isBit=false&useSSL=false";
    private static String db = "starlight";


    private static List<String> include_tables = Lists.newArrayList(
//            "user_auth","user_element","user_info","user_more_info","user_invite","user_task"
    );

    public static void main(String[] args) {
        new AutoGenerator()
                // 全局配置
                .setGlobalConfig(globalConfig())
                // 数据源配置
                .setDataSource(dataSourceConfig())
                // 策略配置
                .setStrategy(strategyConfig())
                // 包配置
                .setPackageInfo(packageConfig())
                //模板
                .setTemplate(templateConfig())
                //自定义xml生成模板
                .setCfg(xmlConfig())
                .execute();
    }
    private static TemplateConfig templateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        //控制 不生成 controller
        templateConfig.setController(null);
        templateConfig.setXml(null);
        return templateConfig;
    }
    private static PackageConfig packageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.starlight");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("model");
        pc.setMapper("mapper");
        return pc;
    }

    private static InjectionConfig xmlConfig() {
        String templatePath = "/templates/mapper.xml.vm";
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = Lists.newArrayList();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return sourcePath() + "/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        injectionConfig.setFileOutConfigList(focList);
        return injectionConfig;
    }

    private static StrategyConfig strategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setTablePrefix(table_prefix);
        // 需要生成的表
        strategy.setInclude(include_tables.toArray(new String[include_tables.size()]));
        strategy.setSuperServiceClass(IService.class);
        strategy.setSuperServiceImplClass(ServiceImpl.class);
        strategy.setSuperMapperClass(null);
        strategy.setEntityLombokModel(true);
        return strategy;
    }

    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName(driver);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setUrl(String.format(url, db));
        return dsc;
    }
    private static GlobalConfig globalConfig() {
        GlobalConfig gc = new GlobalConfig();
        //输出文件路径
        //from gitee https://gitee.com/CodingMonkeys
        gc.setOutputDir(generatorPath());
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columnList
        gc.setBaseColumnList(false);
        gc.setAuthor(au);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        return gc;
    }

    private static String generatorPath() {
        ///Users/tangjian/Project/lucrative-adverts/adverts-service/placement-service/target/test-classes/
        String classPath = ClassLoader.getSystemResource("").getPath();
        System.out.println("classPath: " + classPath);
        /////Users/tangjian/Project/lucrative-adverts/adverts-service/placement-service/
        String modelPath = classPath.replaceAll("/target/test-classes", "");
        System.out.println("modelPath: " + modelPath);
        String generatorPath = modelPath + "src/main/java";
        System.out.println("generatorPath: " + generatorPath);
        return generatorPath;
    }

    private static String sourcePath() {
        ///Users/tangjian/Project/lucrative-adverts/adverts-service/placement-service/target/test-classes/
        String classPath = ClassLoader.getSystemResource("").getPath();
        System.out.println("classPath: " + classPath);
        /////Users/tangjian/Project/lucrative-adverts/adverts-service/placement-service/
        String modelPath = classPath.replaceAll("/target/test-classes", "");
        System.out.println("modelPath: " + modelPath);
        String generatorPath = modelPath + "src/main/resources";
        System.out.println("generatorPath: " + generatorPath);
        return generatorPath;
    }
}
