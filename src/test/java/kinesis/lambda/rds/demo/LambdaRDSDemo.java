//package kinesis.lambda.rds.demo;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.*;
//
///**
// * Created by vwmc on 9/17/17.
// */
//public class LambdaRDSDemo {
//
//    private final String INSTANCE_NAME = "vcrdsinstance";
//    private final String DB_NAME = "VC_RDS_DEMO";
//    private final String USER_NAME = "vc_rds_root";
//    private final String PASSWORD = "vc_rds_root";
//
//    private final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private final String END_POINT = "jdbc:mysql://vcrdsinstance.c0folbvxtjiy.ap-southeast-2.rds.amazonaws.com:3306/" + DB_NAME;
//
//    private Logger logger = LoggerFactory.getLogger(LambdaRDSDemo.class);
//
//    private boolean TABLE_CREATED = true;
//
////    @Test
//    public void testConnection() throws ClassNotFoundException, SQLException {
//
//        logger.info("To Test connection");
//
//        Class.forName(MYSQL_DRIVER);
//        Connection connection = DriverManager.getConnection(END_POINT, USER_NAME, PASSWORD);
//
//        String sql = null;
//
//        if (!TABLE_CREATED) {
//            sql = "CREATE TABLE RDS_DEMO(ID INT, NAME VARCHAR(10))";
//
//            PreparedStatement statement = connection.prepareStatement(sql);
//            int result = statement.executeUpdate();
//            logger.info("executed with result " + result);
//            statement.close();
//
//        } else {
//
//            sql = "INSERT INTO RDS_DEMO VALUES (?,?)";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setInt(1, (int) (Math.random() * 1000));
//            statement.setString(2, "test");
//            int effectedCnt = statement.executeUpdate();
//            logger.info("How many updated : " + effectedCnt);
//        }
//
//        connection.close();
//
//    }
//
////    private AmazonRDS initAmazonRDSClientBuilder() throws Exception {
////        return AmazonRDSClientBuilder.standard()
////                .withCredentials(CredentialUtils.getCredentialsProvider())
////                .withClientConfiguration(ConfigurationUtils.getClientConfigWithUserAgent())
////                .build();
////    }
//
//}
