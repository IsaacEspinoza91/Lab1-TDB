<template>
    <div class="dashboard">
        <h2>Resumen del Sistema</h2>
        <div class="stats-grid">
            <div class="stat-card">
                <h3>Usuarios</h3>
                <p>contar</p>
            </div>
            <div class="stat-card">
                <h3>Farmacias</h3>
                <p>contar</p>
            </div>
            <div class="stat-card">
                <h3>Pedidos Mes</h3>
                <p>contar</p>
            </div>
            <div class="stat-card">
                <h3>Productos</h3>
                <p>contar</p>
            </div>
        </div>
    </div>
</template>

<style scoped>
.stats-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 1.5rem;
    margin-top: 2rem;
}

.stat-card {
    background: #f5f5f5;
    padding: 1.5rem;
    border-radius: 8px;
    text-align: center;
}

.stat-card h3 {
    color: #555;
    margin-bottom: 0.5rem;
}

.stat-card p {
    font-size: 2rem;
    font-weight: bold;
    color: #1a237e;
}
</style>