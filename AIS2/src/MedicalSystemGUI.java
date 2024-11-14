import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

public class MedicalSystemGUI extends JFrame {
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private VisitService visitService;

    private JTabbedPane tabbedPane;
    private JTable patientsTable;
    private JTable doctorsTable;
    private JTable visitsTable;

    private DefaultTableModel patientModel;
    private DefaultTableModel doctorModel;
    private DefaultTableModel visitModel;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public MedicalSystemGUI() {
        // Инициализация репозиториев и сервисов
        patientRepository = new PatientRepository();
        doctorRepository = new DoctorRepository();
        visitService = new VisitService(patientRepository, doctorRepository);

        // Настройка окна
        setTitle("Медицинская Информационная Система");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Создание основных компонентов
        createTabbedPane();

        // Добавление тестовых данных
        addSampleData();
    }

    private void addSampleData() {
    }

    private void createTabbedPane() {
        tabbedPane = new JTabbedPane();

        // Создание вкладок
        tabbedPane.addTab("Пациенты", createPatientsPanel());
        tabbedPane.addTab("Врачи", createDoctorsPanel());
        tabbedPane.addTab("Визиты", createVisitsPanel());

        add(tabbedPane);
    }

    private JPanel createPatientsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Создание модели таблицы
        String[] patientColumns = {"ФИО", "Дата рождения", "Пол", "Адрес", "Телефон", "СНИЛС", "Группа крови"};
        patientModel = new DefaultTableModel(patientColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        patientsTable = new JTable(patientModel);

        // Панель с кнопками
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Добавить");
        JButton editButton = new JButton("Изменить");
        JButton deleteButton = new JButton("Удалить");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Добавление обработчиков
        addButton.addActionListener(e -> showAddPatientDialog());
        editButton.addActionListener(e -> showEditPatientDialog());
        deleteButton.addActionListener(e -> deleteSelectedPatient());

        panel.add(new JScrollPane(patientsTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createDoctorsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Создание модели таблицы
        String[] doctorColumns = {"ФИО", "Специализация", "Категория", "Телефон"};
        doctorModel = new DefaultTableModel(doctorColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        doctorsTable = new JTable(doctorModel);

        // Панель с кнопками
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Добавить");
        JButton editButton = new JButton("Изменить");
        JButton deleteButton = new JButton("Удалить");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Добавление обработчиков
        addButton.addActionListener(e -> showAddDoctorDialog());
        editButton.addActionListener(e -> showEditDoctorDialog());
        deleteButton.addActionListener(e -> deleteSelectedDoctor());

        panel.add(new JScrollPane(doctorsTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createVisitsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Создание модели таблицы
        String[] visitColumns = {"Пациент", "Врач", "Дата и время", "Диагноз", "Назначения"};
        visitModel = new DefaultTableModel(visitColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        visitsTable = new JTable(visitModel);

        // Панель с кнопками
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Добавить");
        JButton editButton = new JButton("Изменить");
        JButton deleteButton = new JButton("Удалить");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Добавление обработчиков
        addButton.addActionListener(e -> showAddVisitDialog());
        editButton.addActionListener(e -> showEditVisitDialog());
        deleteButton.addActionListener(e -> deleteSelectedDoctor());

        panel.add(new JScrollPane(visitsTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void showAddPatientDialog() {
        JDialog dialog = new JDialog(this, "Добавление пациента", true);
        dialog.setLayout(new GridLayout(8, 2));

        JTextField nameField = new JTextField();
        JTextField birthDateField = new JTextField();
        JComboBox<Gender> genderCombo = new JComboBox<>(Gender.values());
        JTextField addressField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField snilsField = new JTextField();
        JComboBox<BloodType> bloodTypeCombo = new JComboBox<>(BloodType.values());

        dialog.add(new JLabel("ФИО:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Дата рождения (гггг-мм-дд):"));
        dialog.add(birthDateField);
        dialog.add(new JLabel("Пол:"));
        dialog.add(genderCombo);
        dialog.add(new JLabel("Адрес:"));
        dialog.add(addressField);
        dialog.add(new JLabel("Телефон:"));
        dialog.add(phoneField);
        dialog.add(new JLabel("СНИЛС:"));
        dialog.add(snilsField);
        dialog.add(new JLabel("Группа крови:"));
        dialog.add(bloodTypeCombo);

        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> {
            try {
                Patient patient = new Patient(
                        nameField.getText(),
                        LocalDate.parse(birthDateField.getText(), dateFormatter),
                        (Gender) genderCombo.getSelectedItem(),
                        addressField.getText(),
                        phoneField.getText(),
                        snilsField.getText(),
                        (BloodType) bloodTypeCombo.getSelectedItem()
                );
                patientRepository.addPatient(patient);
                updatePatientsTable();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Ошибка: " + ex.getMessage());
            }
        });

        dialog.add(saveButton);
        dialog.add(new JButton("Отмена") {{ addActionListener(e -> dialog.dispose()); }});

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showAddDoctorDialog() {
        JDialog dialog = new JDialog(this, "Добавление врача", true);
        dialog.setLayout(new GridLayout(5, 2));

        JTextField nameField = new JTextField();
        JComboBox<Specialization> specCombo = new JComboBox<>(Specialization.values());
        JComboBox<Category> catCombo = new JComboBox<>(Category.values());
        JTextField phoneField = new JTextField();

        dialog.add(new JLabel("ФИО:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Специализация:"));
        dialog.add(specCombo);
        dialog.add(new JLabel("Категория:"));
        dialog.add(catCombo);
        dialog.add(new JLabel("Телефон:"));
        dialog.add(phoneField);

        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> {
            try {
                Doctor doctor = new Doctor(
                        nameField.getText(),
                        (Specialization) specCombo.getSelectedItem(),
                        (Category) catCombo.getSelectedItem(),
                        phoneField.getText()
                );
                doctorRepository.addDoctor(doctor);
                updateDoctorsTable();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Ошибка: " + ex.getMessage());
            }
        });

        dialog.add(saveButton);
        dialog.add(new JButton("Отмена") {{ addActionListener(e -> dialog.dispose()); }});

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void updateDoctorsTable() {
    }

    private void showAddVisitDialog() {
        JDialog dialog = new JDialog(this, "Добавление визита", true);
        dialog.setLayout(new GridLayout(6, 2));

        Vector<String> patientNames = new Vector<>();
        patientRepository.getAllPatients().forEach(p -> patientNames.add(p.getName()));
        JComboBox<String> patientCombo = new JComboBox<>(patientNames);

        Vector<String> doctorNames = new Vector<>();
        doctorRepository.getAllDoctors().forEach(d -> doctorNames.add(d.getName()));
        JComboBox<String> doctorCombo = new JComboBox<>(doctorNames);

        JTextField dateTimeField = new JTextField();
        JTextField diagnosisField = new JTextField();
        JTextField prescriptionsField = new JTextField();

        dialog.add(new JLabel("Пациент:"));
        dialog.add(patientCombo);
        dialog.add(new JLabel("Врач:"));
        dialog.add(doctorCombo);
        dialog.add(new JLabel("Дата и время (гггг-мм-дд чч:мм):"));
        dialog.add(dateTimeField);
        dialog.add(new JLabel("Диагноз:"));
        dialog.add(diagnosisField);
        dialog.add(new JLabel("Назначения (через запятую):"));
        dialog.add(prescriptionsField);

        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> {
            try {
                String[] prescriptionsArray = prescriptionsField.getText().split(",");
                visitService.createVisit(
                        (String) patientCombo.getSelectedItem(),
                        (String) doctorCombo.getSelectedItem(),
                        LocalDateTime.parse(dateTimeField.getText(), dateTimeFormatter),
                        diagnosisField.getText(),
                        new ArrayList<>(Arrays.asList(prescriptionsArray))
                );
                updateVisitsTable();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Ошибка: " + ex.getMessage());
            }
        });

        dialog.add(saveButton);
        dialog.add(new JButton("Отмена") {{ addActionListener(e -> dialog.dispose()); }});

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void updateVisitsTable() {
        
    }

    private void showEditPatientDialog() {
        int selectedRow = patientsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите пациента для редактирования");
            return;
        }
        // Implement edit dialog similar to add dialog but with pre-filled values
    }

    private void showEditDoctorDialog() {
        int selectedRow = doctorsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите врача для редактирования");
            return;
        }
        // Implement edit dialog similar to add dialog but with pre-filled values
    }

    private void showEditVisitDialog() {
        int selectedRow = visitsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите визит для редактирования");
            return;
        }
        // Implement edit dialog similar to add dialog but with pre-filled values
    }

    private void deleteSelectedPatient() {
        int selectedRow = patientsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите пациента для удаления");
            return;
        }

        String snils = (String) patientModel.getValueAt(selectedRow, 5);
        if (JOptionPane.showConfirmDialog(this, "Удалить выбранного пациента?",
                "Подтверждение удаления", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            patientRepository.deletePatientBySnils(snils);
            updatePatientsTable();
        }
    }

    private void updatePatientsTable() {
        // Очищаем таблицу
        patientModel.setRowCount(0);

        // Получаем всех пациентов из репозитория
        List<Patient> patients = patientRepository.getAllPatients();

        // Заполняем таблицу данными пациентов
        for (Patient patient : patients) {
            patientModel.addRow(new Object[]{
                    patient.getName(),
                    patient.getBirthDate(),
                    patient.getGender(),
                    patient.getAddress(),
                    patient.getPhone(),
                    patient.getSnils(),
                    patient.getBloodType()
            });
        }
    }

    private void deleteSelectedDoctor() {
        int selectedRow = doctorsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите врача для удаления");
            return;
        }

        String name = (String) doctorModel.getValueAt(selectedRow, 0);
        if (JOptionPane.showConfirmDialog(this, "Удалить выбранного врача?",
                "Подтверждение удаления", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            doctorRepository.deleteDoctorByName(name);
            updateDoctorsTable();
        }}}