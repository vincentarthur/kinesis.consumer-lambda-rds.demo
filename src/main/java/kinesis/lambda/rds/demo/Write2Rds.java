package kinesis.lambda.rds.demo;

import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import kinesis.lambda.rds.demo.entity.SampleRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by vwmc on 9/17/17.
 */
public class Write2Rds {

    private Logger logger = LoggerFactory.getLogger(Write2Rds.class);

    private final String INSTANCE_NAME = "vcrdsinstance";
    private final String DB_NAME = "VC_RDS_DEMO";
    private final String USER_NAME = "vc_rds_root";
    private final String PASSWORD = "vc_rds_root";

    private final String TABLE_NAME = "RDS_DEMO";

    private final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String END_POINT = "jdbc:mysql://<>/" + DB_NAME;

    private Connection connection = null;

    public void receiveRecords(KinesisEvent event) {

        initConnection();

        event.getRecords().forEach(
                record ->
                        write2RDDS(SampleRecord.fromJsonAsBytes(record.getKinesis().getData().array()))
        );

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Init Connection
     */
    private void initConnection() {
        try {
            Class.forName(MYSQL_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error(e.getLocalizedMessage());
        }

        try {
            connection = DriverManager.getConnection(END_POINT, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            logger.error(e.getSQLState());
        }

    }

    /**
     * @param sampleRecord
     */
    private void write2RDDS(SampleRecord sampleRecord) {

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("INSERT INTO " + TABLE_NAME + " values (?,?)");
            statement.setString(1, sampleRecord.getId());
            statement.setString(2, sampleRecord.getContent());

            int resultCnt = statement.executeUpdate();

            logger.info("Inserted  : " + resultCnt + " record with ID[" + sampleRecord.getId() + "].");

        } catch (SQLException e) {

            logger.error(e.getSQLState());

        } finally {

            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
