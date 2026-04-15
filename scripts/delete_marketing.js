const mysql = require('mysql2/promise');

async function main() {
  const conn = await mysql.createConnection({
    host: '127.0.0.1',
    port: 3306,
    user: 'root',
    password: 'root',
    database: 'test01',
  });

  const [deleted] = await conn.execute(
    "DELETE FROM employees WHERE department = 'Marketing'"
  );
  console.log(`Deleted ${deleted.affectedRows} employee(s) from Marketing department.`);

  const [rows] = await conn.execute('SELECT * FROM employees');
  console.log('\n--- Remaining Employees ---');
  console.table(rows);

  await conn.end();
}

main().catch(err => { console.error(err.message); process.exit(1); });
