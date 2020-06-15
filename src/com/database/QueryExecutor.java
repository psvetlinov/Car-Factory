package com.database;

import com.vehicleInfo.Vehicle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class QueryExecutor implements Queries {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createFactoryTable() {
        String query = "IF NOT EXISTS (SELECT * FROM sysobjects WHERE NAME='Factory' and XTYPE='U')\n" +
                "    CREATE TABLE Factory (\n" +
                "        VIN CHAR(17) PRIMARY KEY,\n" +
                "        Model CHAR(2) NOT NULL ,\n" +
                "        BodyType VARCHAR(10),\n" +
                "        Engine VARCHAR(100) NOT NULL ,\n" +
                "        EmissionStandard VARCHAR(10) NOT NULL ,\n" +
                "        Transmission VARCHAR(100),\n" +
                "        VehicleType VARCHAR(10) NOT NULL ,\n" +
                "        Status CHAR(1) NOT NULL" +
                "    )";
        jdbcTemplate.execute(query);
    }

    @Override
    public String insert(Vehicle vehicle) {
        String query = "INSERT INTO Factory(VIN,Model,BodyType,Engine,EmissionStandard,Transmission,VehicleType,Status)\n" +
                "VALUES(?,?,?,?,?,?,?,?);";
        jdbcTemplate.update(
                query, vehicle.getVIN(),
                vehicle.getModel().getModelName(),
                vehicle.getModel().getBodyType(),
                vehicle.getEngine().toString(),
                vehicle.getEngine().getEmissionStandard(),
                vehicle.getTransmission().toString(),
                vehicle.getType(),
                vehicle.getStatus());
        return "Success";
    }

    @Override
    public List<String> getAllVins() {
        String query = "SELECT VIN FROM Factory";
        List<String> strings = jdbcTemplate.queryForList(query, String.class);
        return strings;
    }

    @Override
    public List<String> getCreatedVehiclesVins() {
        String query = "SELECT VIN FROM Factory WHERE Status='A'";
        List<String> strings = jdbcTemplate.queryForList(query, String.class);
        return strings;
    }

    @Override
    public String delete(String VIN) {
        String updateStatusQuery = "UPDATE Factory SET Status=? WHERE VIN=?";
        jdbcTemplate.update(updateStatusQuery, "D", VIN);
        return "Success";
    }

    @Override
    public String print(String VIN) {
        String query = "SELECT * FROM Factory WHERE VIN=?";
        RowMapper<String> rowMapper = (rs, rowNum) -> {
            StringBuilder stringBuilder = new StringBuilder();
            String vin = rs.getString("VIN");
            String model = rs.getString("Model");
            String bodyType = rs.getString("BodyType");
            stringBuilder.append(vin).append(" ").append(model);
            if (bodyType != null) {
                stringBuilder.append(" ").append(bodyType).append(" ");
            } else {
                stringBuilder.append(" ");
            }
            String engine = rs.getString("Engine");
            String transmission = rs.getString("Transmission");
            String status = rs.getString("Status");
            stringBuilder.append(String.join(" ",engine, transmission, status));
            stringBuilder.append("\n");
            return stringBuilder.toString();
        };
        return jdbcTemplate.queryForObject(query, rowMapper, VIN);
    }

    @Override
    public String printAll() {
        String query = "SELECT * FROM Factory";
        RowMapper<String> rowMapper = getRowMap();
        return jdbcTemplate.queryForObject(query, rowMapper);
    }

    private RowMapper<String> getRowMap() {
        RowMapper<String> rowMapper = (rs, rowNum) -> {
            StringBuilder stringBuilder = new StringBuilder();
            do {
                String vin = rs.getString("VIN");
                String model = rs.getString("Model");
                String bodyType = rs.getString("BodyType");
                stringBuilder.append(vin).append(" ").append(model);
                if (bodyType != null) {
                    stringBuilder.append(" ").append(bodyType).append(" ");
                } else {
                    stringBuilder.append(" ");
                }
                String engine = rs.getString("Engine");
                String transmission = rs.getString("Transmission");
                String status = rs.getString("Status");
                stringBuilder.append(String.join(" ", engine,transmission, status));
                stringBuilder.append("\n");
            } while (rs.next());
            return stringBuilder.toString();
        };
        return rowMapper;
    }

    @Override
    public String getStatus(String VIN) {
        String query = "SELECT Status FROM Factory WHERE VIN=?";
        return jdbcTemplate.queryForObject(query, String.class, VIN);
    }

    @Override
    public String findAll(String emissions) {
        String query = "SELECT * FROM Factory WHERE EmissionStandard=?";
        RowMapper<String> rowMapper = getRowMap();
        return jdbcTemplate.queryForObject(query, rowMapper, emissions);
    }

    @Override
    public String updateEmissions(String VIN, String newEmissionStandard) {
        String query = "UPDATE Factory SET EmissionStandard = ? WHERE VIN =?";
        jdbcTemplate.update(query, newEmissionStandard, VIN);
        return "Successfully updated emission standard";
    }

    @Override
    public String updateEngineEmissions(String VIN, String newEngineEmission) {
        String query = "UPDATE Factory SET Engine = ? WHERE VIN =?";
        jdbcTemplate.update(query,newEngineEmission,VIN);
        return "Successfully updated emission standard";
    }

    @Override
    public String updateTurbo(String VIN, String newTurbo) {
        String query="UPDATE Factory SET Engine=? WHERE VIN=?";
        jdbcTemplate.update(query,newTurbo,VIN);
        return "Successfully updated turbo";
    }

    @Override
    public String updateTransmission(String VIN, String newTransmission) {
        String query="UPDATE Factory SET Engine=? WHERE VIN=?";
        jdbcTemplate.update(query,newTransmission,VIN);
        return "Successfully updated transmission";
    }

    @Override
    public String dropTable() {
        String query="DROP TABLE Factory";
        jdbcTemplate.execute(query);
        return "Success";
    }
}