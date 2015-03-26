%% Homework 4 - Applied Computational Economics
close all
clear all
%% Parameters
alpha   =   4;          %Stock dynamic function's parameter
beta    =   1;          %Stock dynamic function's parameter
gamma   =   .5;         %Inverse demand function's parameter
k       =   .2;         %Harvest cost function's parameter
delta   =   .9;         %Discount factor
%% CompEcon dpsolve
model.func='f';
model.discount=delta;
model.params={alpha beta gamma k};

n=8;                    %number of nodes
a=4;                    %left endpoint
b=8;                    %right endpoint
fspace=fundefn('cheb',n,a,b);
s=funnode(fspace);

v=zeros(n,1);           %initial guess for shadow price
x=zeros(n,1);           %initial guess for actions
[c,s,v,x,resid]=dpsolve(model,fspace,s,v,x);
%% Plot optimal harvest policy
figure
plot(s,x)
title('Optimal Harvest Policy')
xlabel('State')
ylabel('Action')
%% Plot shadow price function
figure
plot(s,v)
title('Shadow Price Function')
xlabel('State')
ylabel('Shadow Price')
%% Plot residual function
figure
plot(s,resid)
title('Resid Function')
xlabel('State')
ylabel('Resid')
%% Resource level simulation over a 20 years horizon
years=[0:20];           %simulation time horizon
ss=4;                   %initial stock of resource
[ssim,xsim]=dpsimul(model,ss,size(years,2)-1,s,x);

figure
plot(years,ssim)
title('Resource Level Simulation')
xlabel('Time')
ylabel('Stock')
