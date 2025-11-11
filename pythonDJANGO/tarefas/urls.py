from django.urls import path
from django.http import HttpResponse
from django.shortcuts import render
from . import views

app_name = "tarefas"

urlpatterns = [
    path('', views.tarefas_home),
    path('adicionar/', views.tarefas_adicionar, name='adicionar')
]