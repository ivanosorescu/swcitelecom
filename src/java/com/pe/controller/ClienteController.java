/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.controller;

import com.pe.DAO.AuxiliarDAO;
import com.pe.model.entity.Auxiliar;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yenny
 */
public class ClienteController extends HttpServlet {

    int id;
    AuxiliarDAO clientdao = new AuxiliarDAO();
    Auxiliar clie = new Auxiliar();
    AuxiliarDAO auxiDAO;
    DecimalFormat formateadorcliente;
    ArrayList<String> listaErrores = new ArrayList<>();

    public ClienteController() {
        auxiDAO = new AuxiliarDAO();
        formateadorcliente = new DecimalFormat("000000");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equals("add")) {
            this.add(request, response);
        }
        if (accion.equals("Actualizar")) {
            this.Edit(request, response);
        }
        if (accion.equals("editar")) {
            this.Editar(request, response);
        }
        if (accion.equals("detalle")) {
            this.Detalle(request, response);
        }
        if (accion.equals("eliminar")) {
            this.eliminar(request, response);
        }
        if (accion.equals("BuscarPorId")) {
            this.BuscarPorId(request, response);
        }
        if (accion.equals("buscarPorIdNS")) {
            this.BuscarPorIdNS(request, response);
        }
        if (accion.equals("BuscarPorIdGRC")) {
            this.BuscarPorIdGRC(request, response);
        }
        if (accion.equals("Estado")) {
            this.Estado(request, response);
        }
    }

    private void BuscarPorId(HttpServletRequest request, HttpServletResponse response)
            throws IOException, IOException {
        int codigo = Integer.parseInt(request.getParameter("idCliente"));
        AuxiliarDAO pdao = new AuxiliarDAO();
        Auxiliar obj = pdao.BuscarPorId(codigo);
        request.getSession().setAttribute("cliente", obj);
        response.sendRedirect("InsertarVenta.jsp");
    }

    private void BuscarPorIdNS(HttpServletRequest request, HttpServletResponse response)
            throws IOException, IOException {
        int codigo = Integer.parseInt(request.getParameter("idClienteNS"));
        AuxiliarDAO pdao = new AuxiliarDAO();
        Auxiliar obj = pdao.BuscarPorId(codigo);
        request.getSession().setAttribute("clienteNS", obj);
        response.sendRedirect("InsertarNotadeSalida.jsp");
    }

    private void BuscarPorIdGRC(HttpServletRequest request, HttpServletResponse response)
            throws IOException, IOException {

        int codigo = Integer.parseInt(request.getParameter("idClientegrc"));
        AuxiliarDAO pdao = new AuxiliarDAO();
        Auxiliar obj = pdao.BuscarPorId(codigo);
        request.getSession().setAttribute("clienteGRC", obj);
        response.sendRedirect("InsertarGuiaremisioncliente.jsp");
    }

    private void Editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("idcli", request.getParameter("id"));
        request.getRequestDispatcher("EditarClientes.jsp").forward(
                request, response);
    }

    private void Detalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("idcli", request.getParameter("id"));
        request.getRequestDispatcher("DetalleCliente.jsp").forward(
                request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Auxiliar cli = new Auxiliar();
        String cliente = request.getParameter("Txtapellidos");
        String correo = request.getParameter("Txtcorreo");
        String telefono = request.getParameter("Txttelefono");
        String celular = request.getParameter("Txtcelular");
        String direccion = request.getParameter("Txtdireccion");
        String Contacto = request.getParameter("Txtcontacto");
        String fechaderegistro = request.getParameter("Txtfechaderegistro");
        String idtipodocumento = request.getParameter("Txtidtipodocumento");
        String numerodocumento = request.getParameter("Txtnumerodocumento");
        String sexo = request.getParameter("Txtsexo");
        cli.setTipoauxi("C");
        int Nclientes = auxiDAO.BuscarNclientes();
        Nclientes = Nclientes + 1;
        String format = formateadorcliente.format(Nclientes);
        cli.setCodigo(format);
        cli.setNombre(cliente);
        cli.setCorreo(correo);
        cli.setTelefono(telefono);
        cli.setCelular(celular);
        cli.setDireccion(direccion);
        cli.setContacto(Contacto);
        cli.setFechaderegistro(fechaderegistro);
        cli.setIdtipodocumento(Integer.parseInt(idtipodocumento));
        cli.setNumerodocumento(numerodocumento);
        cli.setSexo(sexo);
        cli.setEstado("Activo");
        AuxiliarDAO clidao = new AuxiliarDAO();
        boolean validacion = false;
        validacion = clidao.validacion(cli);
        if (validacion == true) {
            response.getWriter().print("yaexiste");
        } else {
            if (clidao.add(cli)) {
                response.getWriter().print("ok");
            } else {
                response.getWriter().print("no ha sido registrado");
            }
        }
    }

    private void Edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        id = Integer.parseInt(request.getParameter("txtid"));
        String Tipoauxi = request.getParameter("txtTipoauxi");
        String codigo = request.getParameter("Txtcodigo");
        String cliente = request.getParameter("Txtapellidos");
        String correo = request.getParameter("Txtcorreo");
        String telefono = request.getParameter("Txttelefono");
        String celular = request.getParameter("Txtcelular");
        String direccion = request.getParameter("Txtdireccion");
        String Contaco = request.getParameter("Txtcontacto");
        String fechaderegistro = request.getParameter("Txtfechaderegistro");
        String idtipodocumento = request.getParameter("Txtidtipodocumento");
        String numerodocumento = request.getParameter("Txtnumerodocumento");
        String sexo = request.getParameter("Txtsexo");
        clie.setIdauxiliar(id);
        clie.setTipoauxi(Tipoauxi);
        clie.setCodigo(codigo);
        clie.setNombre(cliente);
        clie.setCorreo(correo);
        clie.setTelefono(telefono);
        clie.setCelular(celular);
        clie.setDireccion(direccion);
        clie.setContacto(Contaco);
        clie.setFechaderegistro(fechaderegistro);
        clie.setIdtipodocumento(Integer.parseInt(idtipodocumento));
        clie.setNumerodocumento(numerodocumento);
        clie.setSexo(sexo);

        if (clientdao.Edit(clie)) {
            response.getWriter().print("ok");

        } else {
            response.getWriter().print("El cliente no ha sido registrado");

        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        id = Integer.parseInt(request.getParameter("idCli"));
        System.out.println("[ID] " + id);
        clientdao.Eliminar(id);

        AuxiliarDAO clidao = new AuxiliarDAO();
        boolean validacion = false;
        validacion = clidao.Yatienemovimiento(id);
        if (validacion == true) {
            response.getWriter().print("tienemovi");
        }
    }

    private void Estado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        id = Integer.parseInt(request.getParameter("id"));
        String estado = AuxiliarDAO.estado(id);
        if (estado.equalsIgnoreCase("Activo")) {
            clie.setEstado("Desactivado");
        } else {
            clie.setEstado("Activo");
        }
        clientdao.editEstado(clie, id);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
