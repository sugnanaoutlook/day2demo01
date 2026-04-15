const mysql = require('mysql2/promise');

const employees = [
  ['Alice Johnson',  'Engineering',  75000.00],
  ['Bob Smith',      'Marketing',    60000.00],
  ['Carol White',    'HR',           55000.00],
  ['David Brown',    'Engineering',  80000.00],
  ['Eva Martinez',   'Finance',      70000.00],
  ['Frank Lee',      'DevOps',       85000.00],
  ['Grace Kim',      'Marketing',    62000.00],
  ['Henry Wilson',   'HR',           57000.00],
  ['Isla Turner',    'Finance',      72000.00],
  ['Jack Davis',     'Engineering',  78000.00],
];

async function main() {
  const conn = await mysql.createConnection({
    host: '127.0.0.1',
    port: 3306,
    user: 'root',
    password: 'root',
    database: 'test01',
  });

  console.log('Connected to MySQL - test01\n');

  const sql = 'INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)';
  for (const [name, dept, salary] of employees) {
    const [result] = await conn.execute(sql, [name, dept, salary]);
    console.log(`Inserted id=${result.insertId}  ${name} | ${dept} | ${salary}`);
  }

  console.log('\n--- All Employees ---');
  const [rows] = await conn.execute('SELECT * FROM employees');
  console.table(rows);

  await conn.end();
}

main().catch(err => { console.error(err.message); process.exit(1); });
