const baseUrl = 'http://localhost:8080';

function listar(entidade) {
    // Ativar o botão do menu selecionado
    document.querySelectorAll('.menu-item').forEach(btn => {
        btn.classList.remove('active');
    });
    event.currentTarget.classList.add('active');

    const titulo = document.getElementById('titulo');
    const lista = document.getElementById('lista');
    lista.innerHTML = '<div class="loading-state"><i class="fas fa-spinner fa-spin"></i> Carregando dados...</div>';

    let endpoint = entidade;
    if (entidade === 'historico') {
        endpoint = 'historicos';
    }

    titulo.textContent = `Lista de ${entidade.charAt(0).toUpperCase() + entidade.slice(1)}`;

    fetch(`${baseUrl}/${endpoint}`)
        .then(res => {
            if (!res.ok) throw new Error(`Erro ao buscar ${entidade}: ${res.statusText}`);
            return res.json();
        })
        .then(dados => {
            if (!dados.length) {
                lista.innerHTML = '<div class="empty-state"><i class="far fa-folder-open"></i> Nenhum registro encontrado.</div>';
                return;
            }

            let tabelaHTML = '';

            switch(entidade) {
                case 'patios':
                  tabelaHTML = `
                    <table>
                      <thead>
                        <tr>
                          <th>ID</th><th>Nome</th><th>Localização</th><th>Capacidade Máxima</th><th>Administrador</th>
                        </tr>
                      </thead>
                      <tbody>
                        ${dados.map(p => `
                          <tr>
                            <td>${p.id}</td>
                            <td>${p.nome}</td>
                            <td>${p.endereco ? `${p.endereco.cidade}, ${p.endereco.pais}` : '-'}</td>
                            <td>${p.capacidadeMaxima}</td>
                            <td>${p.administradorResponsavel}</td>
                          </tr>`).join('')}
                      </tbody>
                    </table>`;
                  break;

                case 'motos':
                    tabelaHTML = `
                        <div class="table-responsive">
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID</th><th>IMEI</th><th>Chassi</th><th>Placa</th><th>Pátio</th><th>Dispositivo</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    ${dados.map(m => `
                                        <tr>
                                            <td>${m.id}</td>
                                            <td><code>${m.imei}</code></td>
                                            <td>${m.chassi}</td>
                                            <td><span class="badge">${m.placa}</span></td>
                                            <td>${m.patioId || '-'}</td>
                                            <td>${m.dispositivoId || '-'}</td>
                                        </tr>`).join('')}
                                </tbody>
                            </table>
                        </div>`;
                    break;

                case 'dispositivos':
                    tabelaHTML = `
                        <div class="table-responsive">
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID</th><th>Status</th><th>Moto</th><th>Pátio</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    ${dados.map(d => `
                                        <tr>
                                            <td>${d.id}</td>
                                            <td><span class="status-badge ${d.status.toLowerCase()}">${d.status}</span></td>
                                            <td>${d.motoId || '-'}</td>
                                            <td>${d.patioId || '-'}</td>
                                        </tr>`).join('')}
                                </tbody>
                            </table>
                        </div>`;
                    break;

                case 'historico':
                    tabelaHTML = `
                        <div class="table-responsive">
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID</th><th>Moto</th><th>Pátio</th><th>Entrada</th><th>Saída</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    ${dados.map(h => `
                                        <tr>
                                            <td>${h.id}</td>
                                            <td>${h.motoId}</td>
                                            <td>${h.patioId}</td>
                                            <td>${formatarData(h.dataEntrada)}</td>
                                            <td>${formatarData(h.dataSaida)}</td>
                                        </tr>`).join('')}
                                </tbody>
                            </table>
                        </div>`;
                    break;
            }

            lista.innerHTML = tabelaHTML;
        })
        .catch(erro => {
            lista.innerHTML = `<div class="error-state"><i class="fas fa-exclamation-circle"></i> ${erro.message}</div>`;
        });
}

function formatarData(dataISO) {
    if (!dataISO) return '-';
    const dt = new Date(dataISO);
    return dt.toLocaleDateString('pt-BR') + ' ' + dt.toLocaleTimeString('pt-BR', {hour: '2-digit', minute:'2-digit'});
}

// Inicializa com a lista de pátios ao carregar a página
document.addEventListener('DOMContentLoaded', function() {
    // Simula clique no primeiro botão do menu
    document.querySelector('.menu-item').click();
});