package pl.module;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pl.module.exceptions.DaoException;
import pl.module.exceptions.JdbcException;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    public static int numOfBoard;
    private final String url = "jdbc:postgresql://localhost/Sudoku";
    private final String username = "postgres";
    private final String password = "qwerty";
    private final String name;
    private final Connection connection;

    public JdbcSudokuBoardDao(String name) throws JdbcException {
        this.name = name;
        connection = prepareConnection();
    }

    private Connection prepareConnection() throws JdbcException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
        return connection;
    }

    @Override
    public SudokuBoard read() throws JdbcException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        int ID = 0;
        String getBoardId = "select id from boards where sudokuname=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getBoardId)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ID = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new JdbcException(e);
        }

        String insertBoard = "select value, solvedvalue, iseditable from fields where boardid=? and x=? and y=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertBoard)) {
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    preparedStatement.setInt(1, ID);
                    preparedStatement.setInt(2, i);
                    preparedStatement.setInt(3, j);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        if(numOfBoard == 0) {
                            sudokuBoard.set(i, j, resultSet.getInt(1));

                        } else if(numOfBoard == 1) {
                            if(resultSet.getBoolean(3)) {
                                sudokuBoard.set(i, j, 0);
                            } else {
                                sudokuBoard.set(i, j, resultSet.getInt(2));
                            }
                        } else if(numOfBoard == 2) {
                            sudokuBoard.set(i, j, resultSet.getInt(2));
                        }
                    }
                }
            }
            numOfBoard++;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }


        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard obj) throws JdbcException {
        if(numOfBoard == 0) {
            String insertBoard = "insert into boards(sudokuname) values(?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertBoard)) {
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        }

        int ID = 0;
        String getBoardId = "select id from boards where sudokuname=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(getBoardId)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ID = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new JdbcException(e);
        }

        if(numOfBoard == 0) {
            String insertFields = "insert into fields(boardid, value, x, y, solvedvalue, iseditable) values(?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertFields)) {
                for(int i = 0; i < 9; i++) {
                    for(int j = 0; j < 9; j++) {
                        preparedStatement.setInt(1, ID);
                        preparedStatement.setInt(2, obj.get(i, j));
                        preparedStatement.setInt(3, i);
                        preparedStatement.setInt(4, j);
                        preparedStatement.setInt(5, 0);
                        preparedStatement.setBoolean(6, false);
                        preparedStatement.executeUpdate();
                    }
                }
                numOfBoard++;
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        } else if(numOfBoard == 1) {
            String insertFields = "update fields set iseditable = true where boardid = ? and x = ? and y = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertFields)) {
                for(int i = 0; i < 9; i++) {
                    for(int j = 0; j < 9; j++) {
                        if(obj.get(i, j) == 0) {
                            preparedStatement.setInt(1, ID);
                            preparedStatement.setInt(2, i);
                            preparedStatement.setInt(3, j);
                            preparedStatement.executeUpdate();
                        }
                    }
                }
                numOfBoard++;
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        } else if(numOfBoard == 2) {
            String insertFields = "update fields set solvedvalue = ? where boardid = ? and x = ? and y = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertFields)) {
                for(int i = 0; i < 9; i++) {
                    for(int j = 0; j < 9; j++) {
                        preparedStatement.setInt(1, obj.get(i, j));
                        preparedStatement.setInt(2, ID);
                        preparedStatement.setInt(3, i);
                        preparedStatement.setInt(4, j);
                        preparedStatement.executeUpdate();
                    }
                }
                numOfBoard = 0;
            } catch (SQLException e) {
                throw new JdbcException(e);
            }
        }



    }



    @Override
    public void close() throws Exception {
        connection.close();
    }
}
